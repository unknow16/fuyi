package com.fuyi.shop.rpc.mapper;

import com.fuyi.shop.rpc.entity.ShopSpuCategory;
import com.fuyi.shop.rpc.entity.ShopSpuCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopSpuCategoryMapper {
    int countByExample(ShopSpuCategoryExample example);

    int deleteByExample(ShopSpuCategoryExample example);

    int deleteByPrimaryKey(Long catId);

    int insert(ShopSpuCategory record);

    int insertSelective(ShopSpuCategory record);

    List<ShopSpuCategory> selectByExample(ShopSpuCategoryExample example);

    ShopSpuCategory selectByPrimaryKey(Long catId);

    int updateByExampleSelective(@Param("record") ShopSpuCategory record, @Param("example") ShopSpuCategoryExample example);

    int updateByExample(@Param("record") ShopSpuCategory record, @Param("example") ShopSpuCategoryExample example);

    int updateByPrimaryKeySelective(ShopSpuCategory record);

    int updateByPrimaryKey(ShopSpuCategory record);

    List<ShopSpuCategory> selectListByParentId(Long parentId);
}