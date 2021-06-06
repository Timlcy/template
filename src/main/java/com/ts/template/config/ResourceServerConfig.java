package com.ts.template.config;

import com.ts.template.support.MyAccessDeniedHandler;
import com.ts.template.support.MyUserAuthenticationEntryPoint;
import com.ts.template.support.OptionsRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/***
 * 资源服务器配置类
 * @FileName ResourceServerConfig
 * @Company
 * @author MJH
 * @Date 2020年01月09日
 * @version 1.0.0
 * @remark
 * @explain
 *
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final String resourceId = "auth-server";

    @Autowired
    private MyUserAuthenticationEntryPoint myUserAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Value("${personal.auth.pub-key-uri}")
    private String pubKeyUrl;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // 防止iframe嵌套页面无法展示
        http.headers().frameOptions().disable();
        // 只是为了覆盖原有的方法，之后所有的验证还是交由springSecurity处理
        http
                // 由于使用的是jwt，不需要csrf，并支持跨域
                .csrf().disable().cors()
                // 基于token，所以不需要session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 放行相关路径
                .and().authorizeRequests()
                //先放行后端路由跳转的路径
                .antMatchers("/**/**").permitAll()
//                .antMatchers( "/auth/", "/auth/refresh_token", "/auth/client_token","/auth/thirdparty_token"
//                        , "/auth/page/**", "/auth/static/**").permitAll()
//////                /dgwi/page/开头的请求要求认证
//                .antMatchers("/smartSafetyTools/api/**").authenticated()
//                // 其它认证
//                .anyRequest().permitAll()
                // 添加header设置，支持跨域和ajax请求
                .and().headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin", "*"),
                new Header("Access-Control-Expose-Headers", "Authorization"))))
                .and().addFilterAfter(new OptionsRequestFilter(), CorsFilter.class); //拦截OPTIONS
        // 请求，直接返回header
        // 添加自定义异常入口
        http.exceptionHandling().authenticationEntryPoint(myUserAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //设置用于解码的非对称加密的公钥
        converter.setVerifierKey(getKeyFromAuthorizationServer());
        return converter;
    }

    private String getKeyFromAuthorizationServer() {
        ObjectMapper objectMapper = new ObjectMapper();
        String pubKey = new RestTemplate().getForObject(pubKeyUrl, String.class);
        try {
            Map map = objectMapper.readValue(pubKey, Map.class);
            return map.get("value").toString();
        } catch (IOException e) {
            log.error("获取联网公钥失败.");
        }
        return null;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
        resources.resourceId(resourceId);
    }

}
