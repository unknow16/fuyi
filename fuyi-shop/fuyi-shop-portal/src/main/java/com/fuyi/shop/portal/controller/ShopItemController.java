package com.fuyi.shop.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.framework.web.base.BaseResult;
import com.fuyi.shop.rpc.api.IShopItemService;
import com.fuyi.shop.rpc.entity.ShopItem;
import com.fuyi.shop.rpc.entity.ShopItemCat;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName ShopItemController
 * @Description 商品管理控制器
 * @Author fuyi
 * @Date 2019/1/2 17:32
 * @Version 1.0
 */
@Controller
@RequestMapping("/item")
public class ShopItemController {

    @Reference
    private IShopItemService shopItemService;

    @RequestMapping("/getItemCatListByParentId")
    @ResponseBody
    public BaseResult getItemCatListByParentId(Long parentId) {
        List<ShopItemCat> itemCatList = shopItemService.getItemCatListByParentId(parentId);
        return BaseResult.ok(itemCatList);
    }

    @RequestMapping("/getItemCatList")
    @ResponseBody
    public BaseResult getItemCatList() {
        List<ShopItemCat> itemCatList = shopItemService.getItemCatList();
        return BaseResult.ok(itemCatList);
    }


    @RequestMapping(value = "/toItem/{id}")
    public ModelAndView toItem(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("item/item");

        ShopItem shopItem = shopItemService.selectByPrimaryKey(id);
        modelAndView.addObject("item", shopItem);
        return modelAndView;
    }
}
