package com.fuyi.shop.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.common.base.BaseResult;
import com.fuyi.common.vo.EasyUIDataGridResult;
import com.fuyi.common.vo.EasyUITreeNode;
import com.fuyi.shop.rpc.api.IShopSpuBrandService;
import com.fuyi.shop.rpc.api.IShopSpuDescService;
import com.fuyi.shop.rpc.api.IShopSpuService;
import com.fuyi.shop.rpc.entity.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SpuController
 * @Description 商品管理控制器
 * @Author fuyi
 * @Date 2019/1/8 17:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

    @Reference(timeout = 9000)
    private IShopSpuService spuService;

    @Reference(timeout = 9000)
    private IShopSpuBrandService spuBrandService;

    @Reference(timeout = 9000)
    private IShopSpuDescService spuDescService;

    @RequestMapping("/list")
    public EasyUIDataGridResult getSpuList(Integer page, Integer rows) {
        ShopSpuExample shopSpuExample = new ShopSpuExample();
        List<ShopSpu> shopSpus = spuService.selectByExampleForStartPage(shopSpuExample, page, rows);
        int total = spuService.countByExample(shopSpuExample);
        return EasyUIDataGridResult.build(total, shopSpus);
    }

    @RequestMapping("/save")
    public BaseResult saveSpu(ShopSpu spu) {
        int count = spuService.insertSelective(spu);
        return BaseResult.ok("新增商品成功!", count);
    }

    @RequestMapping("/saveDesc")
    public BaseResult saveSpuDesc(ShopSpuDesc spuDesc) {
        int count = spuDescService.insertSelective(spuDesc);
        return BaseResult.ok("新增商品详情成功!", count);
    }

    @RequestMapping("/cat/list")
    public List<EasyUITreeNode> getSpuCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
        return spuService.getSpuCatList(parentId);
    }

    @RequestMapping("/brand/list")
    public EasyUIDataGridResult getSpuBrandList(Integer page, Integer rows) {
        ShopSpuBrandExample shopSpuBrandExample = new ShopSpuBrandExample();
        List<ShopSpuBrand> shopSpuBrands = spuBrandService.selectByExampleForStartPage(shopSpuBrandExample, page, rows);
        int total = spuBrandService.countByExample(shopSpuBrandExample);
        return EasyUIDataGridResult.build(total, shopSpuBrands);
    }
}
