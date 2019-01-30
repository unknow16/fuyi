package com.fuyi.shop.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @Reference
//    private IShopItemService shopItemService;
//
//    @RequestMapping("/getItemCatListByParentId")
//    @ResponseBody
//    public BaseResult getItemCatListByParentId(Long parentId) {
//        List<ShopItemCat> itemCatList = shopItemService.getItemCatListByParentId(parentId);
//        return BaseResult.ok(itemCatList);
//    }
//
//    @RequestMapping("/getItemCatList")
//    @ResponseBody
//    public BaseResult getItemCatList() {
//        List<ShopItemCat> itemCatList = shopItemService.getItemCatList();
//        return BaseResult.ok(itemCatList);
//    }
//
//
//    @RequestMapping(value = "/toItem/{id}")
//    public ModelAndView toItem(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("item/item");
//
//        ShopItem shopItem = shopItemService.selectByPrimaryKey(id);
//        modelAndView.addObject("item", shopItem);
//        return modelAndView;
//    }
}
