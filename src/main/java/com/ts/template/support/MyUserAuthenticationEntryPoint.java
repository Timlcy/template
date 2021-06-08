package com.ts.template.support;

import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/***
 * 没有携带token的处理类
 * @FileName MyUserAuthenticationEntryPoint
 * @Company
 * @author MJH
 * @Date 2020年01月09日
 * @version 1.0.0
 * @remark 匿名用户访问无权限资源时，返回登录页面
 * @explain
 *
 */
@Component
public class MyUserAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        Map<String, Object> map = new HashMap<>();
        Throwable cause = authException.getCause();
        if (cause instanceof InvalidTokenException) {
            map.put("code", 401);//401
            map.put("msg", "无效的token");

        } else {
            map.put("code", 401);//401
            map.put("msg", "请求头没有token令牌，请先登录");
        }
        map.put("data", authException.getMessage());
        map.put("success", false);
        map.put("path", request.getRequestURI());
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

}
