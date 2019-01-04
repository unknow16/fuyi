package com.fuyi.shop.rpc.mapper;

import com.fuyi.shop.rpc.entity.ShopItem;
import com.fuyi.shop.rpc.entity.ShopItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopItemMapper {
    int countByExample(ShopItemExample example);

    int deleteByExample(ShopItemExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(ShopItem record);

    int insertSelective(ShopItem record);

    List<ShopItem> selectByExample(ShopItemExample example);

    ShopItem selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") ShopItem record, @Param("example") ShopItemExample example);

    int updateByExample(@Param("record") ShopItem record, @Param("example") ShopItemExample example);

    int updateByPrimaryKeySelective(ShopItem record);

    int updateByPrimaryKey(ShopItem record);
}