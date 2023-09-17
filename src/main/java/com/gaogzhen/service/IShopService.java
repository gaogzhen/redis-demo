package com.gaogzhen.service;

import com.gaogzhen.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IShopService extends IService<Shop> {

    /**
     * 查询商品
     * @param id 商品id
     * @return 商品
     */
    Shop queryById(Long id);
}
