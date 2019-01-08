package com.fuyi.shop.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.common.vo.EasyUITreeNode;
import com.fuyi.shop.rpc.api.IShopSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName SpuController
 * @Description 商品管理控制器
 * @Author fuyi
 * @Date 2019/1/8 17:39
 * @Version 1.0
 */
@Controller
@RequestMapping("/spu")
public class SpuController {

    @Reference(timeout = 9000)
    private IShopSpuService spuService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getSpuCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
        return spuService.getSpuCatList(parentId);
    }
}
