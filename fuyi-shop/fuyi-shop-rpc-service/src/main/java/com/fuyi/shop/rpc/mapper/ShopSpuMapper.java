package com.fuyi.shop.rpc.mapper;

import com.fuyi.shop.rpc.entity.ShopSpu;
import com.fuyi.shop.rpc.entity.ShopSpuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopSpuMapper {
    int countByExample(ShopSpuExample example);

    int deleteByExample(ShopSpuExample example);

    int deleteByPrimaryKey(Long spuId);

    int insert(ShopSpu record);

    int insertSelective(ShopSpu record);

    List<ShopSpu> selectByExample(ShopSpuExample example);

    ShopSpu selectByPrimaryKey(Long spuId);

    int updateByExampleSelective(@Param("record") ShopSpu record, @Param("example") ShopSpuExample example);

    int updateByExample(@Param("record") ShopSpu record, @Param("example") ShopSpuExample example);

    int updateByPrimaryKeySelective(ShopSpu record);

    int updateByPrimaryKey(ShopSpu record);
}