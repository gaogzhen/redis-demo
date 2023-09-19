package com.gaogzhen.utils;

/**
 * 常量类
 * @author gaoguangzhen.ex
 * @since 2023/9/19 18:00
 */
public class Constants {
    /**
     * ========== 布隆过滤器
     */

    /**
     * 商品过滤器名称
     */
    public static String SHOP_FILTER_NAME = "shop-filter";

    /**
     * 预期插入数量
     */
    public static long EXPECTED_INSERTIONS = 1000L;
    /**
     * 误判率
     */

    public static double FALSE_PROBABILITY = 0.001;

    /**
     * 非法请求所返回的JSON
     */
    public static String ILLEGAL_JSON = "[\"com.company.springboot.entity.Shop\",{\"id\":null,\"name\":\"null\"}]";

}
