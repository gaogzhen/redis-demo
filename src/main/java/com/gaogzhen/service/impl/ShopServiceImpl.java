package com.gaogzhen.service.impl;

import com.gaogzhen.entity.Shop;
import com.gaogzhen.mapper.ShopMapper;
import com.gaogzhen.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gaogzhen.utils.BloomFilterUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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
    private BloomFilterUtil bloomFilterUtil;

    /**
     * 初始化布隆过滤器
     */
    @PostConstruct
    public void init() {

    }

    @Override
    public Shop queryById(Long id) {
        return null;
    }
}
