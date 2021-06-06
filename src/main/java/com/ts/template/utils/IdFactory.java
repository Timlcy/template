package com.ts.template.utils;

import cn.hutool.core.lang.Snowflake;
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
     * Twitter的Snowflake 算法
     *
     * @return 分布式ID
     */
    public static String getSnowflake() {
        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = IdUtil.getSnowflake(0, 0);
        long id = snowflake.nextId();
        return id + "";
    }


}
