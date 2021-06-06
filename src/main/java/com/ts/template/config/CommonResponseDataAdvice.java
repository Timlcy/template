package com.ts.template.config;

import cn.hutool.json.JSONUtil;
import com.ts.template.utils.ResCode;
import com.ts.template.utils.ResultEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestControllerAdvice(basePackages = "com.ts.template.controller")
@Slf4j
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<?
            extends HttpMessageConverter<?>> aClass) {
        //如果类有IgnoreResponseAdvice这个注解就不处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        //如果方法有IgnoreResponseAdvice这个注解就不处理
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        return true;
    }

    //Object，这个就是原始的Controller返回的内容。我们也就是需要对它进行包装
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (!(o instanceof ResultEntry)) {
            ResultEntry responseResult = new ResultEntry(200, o);
            //因为handler处理类的返回类型是String，为了保证一致性，这里需要将ResponseResult转回去
            if (o instanceof String) {
                return JSONUtil.toJsonPrettyStr(responseResult);
            }
            return responseResult;
        }
        return o;
    }

    /**
     * 全局捕获异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    public Object unknownException(Exception e, HttpServletRequest request) {
        log.error("异常URL:" + request.getRequestURL());
        Enumeration<String> er = request.getHeaderNames();//获取请求头的所有name值
        StringBuilder sb = new StringBuilder();
        while (er.hasMoreElements()) {
            String name = er.nextElement();
            String value = request.getHeader(name);
            sb.append(name + "=" + value+"\n");
        }
        log.error("异常Header头:" +sb.toString());
        StringBuilder sb2 = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            sb2.append(paramName + "=" + paramValue+"\n");
        }
        log.error("异常Parameter:" +sb2.toString());
        log.error("异常堆栈:", e);
        return new ResultEntry<>(ResCode.CODE_7567,
                ResCode.getVal(ResCode.CODE_7567) + System.getProperty("line.separator") + e.getMessage());
    }

}
