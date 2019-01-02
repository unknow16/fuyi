package com.fuyi.shop.manager.mapper;

import com.fuyi.shop.manager.entity.ShopItem;
import com.fuyi.shop.manager.entity.ShopItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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