package com.fuyi.shop.rpc.mapper;

import com.fuyi.shop.rpc.entity.ShopSpuBrand;
import com.fuyi.shop.rpc.entity.ShopSpuBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopSpuBrandMapper {
    int countByExample(ShopSpuBrandExample example);

    int deleteByExample(ShopSpuBrandExample example);

    int deleteByPrimaryKey(Long brandId);

    int insert(ShopSpuBrand record);

    int insertSelective(ShopSpuBrand record);

    List<ShopSpuBrand> selectByExample(ShopSpuBrandExample example);

    ShopSpuBrand selectByPrimaryKey(Long brandId);

    int updateByExampleSelective(@Param("record") ShopSpuBrand record, @Param("example") ShopSpuBrandExample example);

    int updateByExample(@Param("record") ShopSpuBrand record, @Param("example") ShopSpuBrandExample example);

    int updateByPrimaryKeySelective(ShopSpuBrand record);

    int updateByPrimaryKey(ShopSpuBrand record);
}