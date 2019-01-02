package com.fuyi.shop.manager.service.impl;

import com.fuyi.shop.manager.entity.ShopItemCat;
import com.fuyi.shop.manager.entity.ShopItemCatExample;
import com.fuyi.shop.manager.mapper.ShopItemCatMapper;
import com.fuyi.shop.manager.service.IShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ShopItemCatServiceImpl
 * @Description 商品管理Service
 * @Author Administrator
 * @Date 2019/1/2 17:33
 * @Version 1.0
 */
@Service
public class ShopItemServiceImpl implements IShopItemService {

    @Autowired
    private ShopItemCatMapper shopItemCatMapper;

    @Override
    public List<ShopItemCat> getItemCatListByParentId(Long parentId) {
        return null;
    }

    @Override
    public List<ShopItemCat> getItemCatList() {
        ShopItemCatExample shopItemCatExample = new ShopItemCatExample();
        shopItemCatExample.createCriteria().andStatusEqualTo(1);
        return null;
    }
}
