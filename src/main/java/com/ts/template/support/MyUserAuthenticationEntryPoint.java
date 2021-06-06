package com.ts.template.support;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String url = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(url);
        String pathPatter = "/*/page/**";
        System.out.println(pathPatter);
        if (pathMatcher.match(pathPatter, url)) {
            System.out.println("路由不对跳转到登录页面");
            response.sendRedirect("/auth/page/login?url=" + url);
        } else {
            String errMsg = "请求头没有token令牌，请先登录";
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            JSONObject res = new JSONObject();
            res.put("state", "false");
            res.put("code", HttpServletResponse.SC_UNAUTHORIZED);
            res.put("msg", errMsg);
            response.getOutputStream().write(JSONUtil.toJsonPrettyStr(res).getBytes(StandardCharsets.UTF_8));
        }
    }
}
