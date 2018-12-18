package com.fuyi.shop.manager.mapper;

import com.fuyi.shop.manager.entity.ShopItemCat;

public interface ShopItemCatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopItemCat record);

    int insertSelective(ShopItemCat record);

    ShopItemCat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopItemCat record);

    int updateByPrimaryKey(ShopItemCat record);
}