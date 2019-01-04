package com.fuyi.shop.rpc.mapper;

import com.fuyi.shop.rpc.entity.ShopItemCat;
import com.fuyi.shop.rpc.entity.ShopItemCatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopItemCatMapper {
    int countByExample(ShopItemCatExample example);

    int deleteByExample(ShopItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopItemCat record);

    int insertSelective(ShopItemCat record);

    List<ShopItemCat> selectByExample(ShopItemCatExample example);

    ShopItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopItemCat record, @Param("example") ShopItemCatExample example);

    int updateByExample(@Param("record") ShopItemCat record, @Param("example") ShopItemCatExample example);

    int updateByPrimaryKeySelective(ShopItemCat record);

    int updateByPrimaryKey(ShopItemCat record);

    List<ShopItemCat> selectTreeByParentId(Long parentId);
}