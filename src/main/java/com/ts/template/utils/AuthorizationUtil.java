package com.ts.template.utils;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Objects;

/**
 * @ClassName AuthorizationUtil
 * @Description 授权解析工具类
 * @Author 林春永
 * @Date 2021/3/23
 * @Version 1.0
 **/
public class AuthorizationUtil {


    public static JSONObject resolving() {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization!=null&&!authorization.equals("undefined")) {
            String bearer_ = authorization.replace("bearer ", "");
            String[] split = bearer_.split("\\.");
            JSONObject userJson =
                    (JSONObject) JSONUtil.parse(new String(Base64.getDecoder().decode(split[1])));
            return userJson;
        } else {
            return null;
        }


    }


    public static void main(String[] args) {
        String token="bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJvdGhlckF1dGgiOltdLCJhb3AiOm51bGwsInVzZXJfbmFtZSI6ImFkbWluIiwicGFnZUF1dGgiOltdLCJpc3MiOiJkZmRrIiwiZGlzdFJvb20iOiIxMDE4MDgyIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJjbGllbnRfaWQiOiJka19jbGllbnRfMSIsImF1ZCI6WyJhdXRoLXNlcnZlciJdLCJzY29wZSI6WyJBTEwiXSwiaWQiOiIwNmQ1OTdhYzQ4ZDY0MjFkYjU0YjdhNTQ1M2M3ZjE0YSIsImV4cCI6MTYyMDcwMjQwMCwianRpIjoiOTMwZGNlMmUtMWU4MC00ODgxLWJhMjMtMmRjOWE0YjVlM2Y1In0.D9_tFB8j-CjPPUP4TPK2rd-nAPIABdicj9YSxYzrHVL5mg7sZDEdetKaB9YbAPL5qE8pfdqt_80ge6cLXbpebbYk18u7ub7iMp2xM1H6ptJP7InZwKoVJ16u6OSp9v_rNAm49zH3--pXtYiASC2vvChFW1ukqKusoQvYXO-qm2mMfSPKo5bZx1BkExiaSKxvjtqzJjxW5-og0j0APgBC53_W4jQxidSjtj2ee1zoxvGu1Sra93M6gSeMFzyGJjYVo3amZ7m63uNadR62rsKGIFwr8G_GNoVMeCUXv2APSpnJ3KfzavcD2LWMcY9fyeHS_X9O_yZEJaPSy7or1nrdKw";
        String bearer_ = token.replace("bearer ", "");
        String[] split = bearer_.split("\\.");
        System.out.println();
        JSON userJson = JSONUtil.parse(new String(Base64.getDecoder().decode(split[1])));
        System.out.println(((JSONObject) userJson).get("user_name"));
        System.out.println(((JSONObject) userJson).get("dist_room"));

    }

}
