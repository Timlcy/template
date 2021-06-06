package com.ts.template.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @ClassName IdFactory
 * @Description Id生成工厂
 * @Author 林春永
 * @Date 2021/4/26
 * @Version 1.0
 **/
public class IdFactory {

    /**
     * 生成资产编号
     *
     * @param id 类型ID
     * @return 资产编号
     */
    public static String getSpecification(String id) {
        return id + '-' + IdUtil.simpleUUID();
    }


    /**
     * 生成简单UUID
     *
     * @return 生成简单UUID
     */
    public static String getSimpleUUID() {
        return IdUtil.simpleUUID();
    }




}
