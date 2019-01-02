package com.fuyi.shop.manager.controller;

import com.fuyi.framework.web.base.BaseResult;
import com.fuyi.shop.manager.entity.ShopItemCat;
import com.fuyi.shop.manager.service.IShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ShopItemController
 * @Description 商品管理控制器
 * @Author fuyi
 * @Date 2019/1/2 17:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/item")
public class ShopItemController {

    @Autowired
    private IShopItemService shopItemService;

    @RequestMapping("/getItemCatListByParentId")
    public BaseResult getItemCatListByParentId(Long parentId) {
        List<ShopItemCat> itemCatList = shopItemService.getItemCatListByParentId(parentId);
        return BaseResult.ok(itemCatList);
    }

    @RequestMapping("/getItemCatList")
    public BaseResult getItemCatList() {
        List<ShopItemCat> itemCatList = shopItemService.getItemCatList();
        return BaseResult.ok(itemCatList);
    }

}
