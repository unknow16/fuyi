package com.fuyi.shop.manager.service.impl;

import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.shop.manager.entity.ShopItem;
import com.fuyi.shop.manager.entity.ShopItemCat;
import com.fuyi.shop.manager.entity.ShopItemCatExample;
import com.fuyi.shop.manager.entity.ShopItemExample;
import com.fuyi.shop.manager.mapper.ShopItemCatMapper;
import com.fuyi.shop.manager.mapper.ShopItemMapper;
import com.fuyi.shop.manager.service.IShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: fuyi
 * @Date: 2019/1/2 21:02
 * @Description:
 */
@Service
public class ShopItemServiceImpl extends BaseServiceImpl<ShopItemMapper, ShopItem, ShopItemExample> implements IShopItemService {

    @Autowired
    private ShopItemCatMapper shopItemCatMapper;

    @Override
    public List<ShopItemCat> getItemCatListByParentId(Long parentId) {
        ShopItemCatExample shopItemCatExample = new ShopItemCatExample();
        shopItemCatExample.createCriteria().andStatusEqualTo(1).andParentIdEqualTo(parentId);

        List<ShopItemCat> shopItemCats = shopItemCatMapper.selectByExample(shopItemCatExample);
        return shopItemCats;
    }

    @Override
    public List<ShopItemCat> getItemCatList() {
        List<ShopItemCat> shopItemCats = shopItemCatMapper.selectTreeByParentId(0L);
        return shopItemCats;
    }
}
