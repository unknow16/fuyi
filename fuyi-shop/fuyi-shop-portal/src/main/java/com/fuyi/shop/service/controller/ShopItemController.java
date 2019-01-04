package com.fuyi.shop.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.framework.web.base.BaseResult;
import com.fuyi.shop.rpc.api.IShopItemService;
import com.fuyi.shop.rpc.entity.ShopItemCat;
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

    @Reference
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
