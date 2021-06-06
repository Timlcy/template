package com.ts.template.support;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/***
 * 无权访问处理类
 * @FileName MyAccessDeniedHandler
 * @Company
 * @author MJH
 * @Date 2020年01月13日
 * @version 1.0.0
 * @remark
 * @explain
 *
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        String msg = "无权访问此接口";
        JSONObject res = new JSONObject();
        res.put("state", "false");
        res.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        res.put("msg", msg);
        response.getOutputStream().write(JSONUtil.toJsonPrettyStr(res).getBytes(StandardCharsets.UTF_8));
    }

}
