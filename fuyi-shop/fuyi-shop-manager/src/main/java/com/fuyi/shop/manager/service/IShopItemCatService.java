package com.fuyi.shop.manager.service;

import java.util.List;

/**
 * @ClassName IShopItemCatService
 * @Description 电商系统后台管理分类service接口
 * @Author fuyi
 * @Date 2018/12/18 16:04
 * @Version 1.0
 */
public interface IShopItemCatService {
    /**
     * 根据父id获取子级列表
     * @param parentId
     * @return
     */
    List getItemCatList(Long parentId);
}
