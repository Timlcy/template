package com.ts.template.utils;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UrlPathUtils
 * @Description
 * @Author 林春永
 * @Date 2020/12/15
 * @Version 1.0
 **/
public class UrlPathUtils {


    public static String[] getControllerPath(String classFullName) throws ClassNotFoundException {
        List<String> list = new ArrayList<>();
        Class cls = Class.forName(classFullName);
        RequestMapping requestMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
        System.out.println(requestMapping.value()[0]);
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            list.add(requestMapping.value()[0] + annotation.value()[0]);
        }
        return list.toArray(new String[list.size()]);
    }

}
