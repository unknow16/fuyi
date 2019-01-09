package com.fuyi.shop.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.shop.rpc.api.IShopSpuBrandService;
import com.fuyi.shop.rpc.entity.ShopSpuBrand;
import com.fuyi.shop.rpc.entity.ShopSpuBrandExample;
import com.fuyi.shop.rpc.mapper.ShopSpuBrandMapper;

/**
 * @ClassName ShopSpuBrandServiceImpl
 * @Description 品牌管理
 * @Author fuyi
 * @Date 2019/1/9 11:11
 * @Version 1.0
 */
@Service
@BaseServiceAnnotation
public class ShopSpuBrandServiceImpl extends BaseServiceImpl<ShopSpuBrandMapper, ShopSpuBrand, ShopSpuBrandExample> implements IShopSpuBrandService {
}
