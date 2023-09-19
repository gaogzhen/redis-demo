package com.gaogzhen.service.impl;

import com.gaogzhen.entity.Shop;
import com.gaogzhen.mapper.ShopMapper;
import com.gaogzhen.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gaogzhen.utils.BloomFilterUtil;
import com.gaogzhen.utils.Constants;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private BloomFilterUtil bloomFilterUtil;

    private RBloomFilter<Long> bloomFilter = null;

    /**
     * 初始化布隆过滤器
     */
    @PostConstruct
    public void init() {
        // 启动项目时初始化bloomFilter
        List<Shop> shopList = this.list();
        bloomFilter = bloomFilterUtil.create(Constants.SHOP_FILTER_NAME, Constants.EXPECTED_INSERTIONS, Constants.FALSE_PROBABILITY);
        for (Shop shop : shopList) {
            bloomFilter.add(shop.getId());
        }

    }

    @Cacheable(cacheNames = "shop", key = "#id", unless = "#result==null")
    @Override
    public Shop queryById(Long id) {
        // bloomFilter中不存在该key,为非法访问
        if (!bloomFilter.contains(id)) {
            System.out.println("所要查询的数据既不在缓存中，也不在数据库中，为非法key");
            /**
             * 设置unless = "#result==null"并在非法访问的时候返回null的目的是不将该次查询返回的null使用
             * RedissonConfig-->RedisCacheManager-->RedisCacheConfiguration-->entryTtl设置的过期时间存入缓存。
             *
             * 因为那段时间太长了，在那段时间内可能该非法key又添加到bloomFilter，比如之前不存在id为1234567的用户，
             * 在那段时间可能刚好id为1234567的用户完成注册，使该key成为合法key。
             *
             * 所以我们需要在缓存中添加一个可容忍的短期过期的null或者是其它自定义的值,使得短时间内直接读取缓存中的该值。
             *
             * 因为Spring Cache本身无法缓存null，因此选择设置为一个其中所有值均为null的JSON，
             */
            redissonClient.getBucket("user::" + id, new StringCodec()).set(Constants.ILLEGAL_JSON, new Random().nextInt(200) + 300, TimeUnit.SECONDS);
            return null;
        }
        // 不是非法访问，可以访问数据库
        System.out.println("数据库中得到数据*****");
        return baseMapper.selectById(id);
    }
}
