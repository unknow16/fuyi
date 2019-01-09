package com.fuyi.shop.rpc.mapper;

import com.fuyi.shop.rpc.entity.ShopSpuDesc;
import com.fuyi.shop.rpc.entity.ShopSpuDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopSpuDescMapper {
    int countByExample(ShopSpuDescExample example);

    int deleteByExample(ShopSpuDescExample example);

    int deleteByPrimaryKey(Integer spuId);

    int insert(ShopSpuDesc record);

    int insertSelective(ShopSpuDesc record);

    List<ShopSpuDesc> selectByExampleWithBLOBs(ShopSpuDescExample example);

    List<ShopSpuDesc> selectByExample(ShopSpuDescExample example);

    ShopSpuDesc selectByPrimaryKey(Integer spuId);

    int updateByExampleSelective(@Param("record") ShopSpuDesc record, @Param("example") ShopSpuDescExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopSpuDesc record, @Param("example") ShopSpuDescExample example);

    int updateByExample(@Param("record") ShopSpuDesc record, @Param("example") ShopSpuDescExample example);

    int updateByPrimaryKeySelective(ShopSpuDesc record);

    int updateByPrimaryKeyWithBLOBs(ShopSpuDesc record);

    int updateByPrimaryKey(ShopSpuDesc record);
}