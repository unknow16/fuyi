package com.fuyi.shop.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.shop.rpc.api.IShopItemService;
import com.fuyi.shop.rpc.entity.ShopItem;
import com.fuyi.shop.rpc.entity.ShopItemCat;
import com.fuyi.shop.rpc.entity.ShopItemCatExample;
import com.fuyi.shop.rpc.entity.ShopItemExample;
import com.fuyi.shop.rpc.mapper.ShopItemCatMapper;
import com.fuyi.shop.rpc.mapper.ShopItemMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: fuyi
 * @Date: 2019/1/2 21:02
 * @Description:
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class ShopItemServiceImpl implements IShopItemService {

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
