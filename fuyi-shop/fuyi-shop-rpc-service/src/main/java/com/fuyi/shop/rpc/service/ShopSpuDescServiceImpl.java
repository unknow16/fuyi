package com.fuyi.shop.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.shop.rpc.api.IShopSpuDescService;
import com.fuyi.shop.rpc.entity.ShopSpuDesc;
import com.fuyi.shop.rpc.entity.ShopSpuDescExample;
import com.fuyi.shop.rpc.mapper.ShopSpuDescMapper;

/**
 * @ClassName ShopSpuDescServiceImpl
 * @Description 商品详情
 * @Author fuyi
 * @Date 2019/1/9 18:21
 * @Version 1.0
 */
@Service
@BaseServiceAnnotation
public class ShopSpuDescServiceImpl extends BaseServiceImpl<ShopSpuDescMapper, ShopSpuDesc, ShopSpuDescExample> implements IShopSpuDescService {
}
