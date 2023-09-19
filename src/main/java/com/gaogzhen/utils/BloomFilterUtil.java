package com.gaogzhen.utils;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 布隆过滤器工具类
 * @author gaogzhen
 * @since 2023/9/19 17:56
 */
@Component
public class BloomFilterUtil {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 创建布隆过滤器
     *
     * @param filterName         过滤器名称
     * @param expectedInsertions 预测插入数量
     * @param falseProbability  误判率
     */
    public <T> RBloomFilter<T> create(String filterName, long expectedInsertions, double falseProbability) {
        RBloomFilter<T> bloomFilter = redissonClient.getBloomFilter(filterName);
        bloomFilter.tryInit(expectedInsertions, falseProbability);
        return bloomFilter;
    }
}
