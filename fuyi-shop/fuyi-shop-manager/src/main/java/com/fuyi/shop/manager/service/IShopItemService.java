package com.fuyi.shop.manager.service;

import com.fuyi.framework.api.base.BaseService;
import com.fuyi.shop.manager.entity.ShopItemCat;
import com.fuyi.shop.manager.entity.ShopItemCatExample;

import java.util.List;

/**
 * @ClassName IShopItemCatService
 * @Description 电商系统后台管理分类service接口
 * @Author fuyi
 * @Date 2018/12/18 16:04
 * @Version 1.0
 */
public interface IShopItemService {//extends BaseService<ShopItemCat, ShopItemCatExample> {
    /**
     * 根据父id获取子级列表
     * @param parentId
     * @return
     */
    List<ShopItemCat> getItemCatListByParentId(Long parentId);

    /**
     * 获取商品分类列表
     */
    List<ShopItemCat> getItemCatList();
}
