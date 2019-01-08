package com.fuyi.shop.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.common.vo.EasyUITreeNode;
import com.fuyi.shop.rpc.api.IShopSpuService;
import com.fuyi.shop.rpc.entity.ShopSpu;
import com.fuyi.shop.rpc.entity.ShopSpuCategory;
import com.fuyi.shop.rpc.entity.ShopSpuExample;
import com.fuyi.shop.rpc.mapper.ShopSpuCategoryMapper;
import com.fuyi.shop.rpc.mapper.ShopSpuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ShopSpuServiceImpl
 * @Description 商品管理service实现
 * @Author fuyi
 * @Date 2019/1/8 18:32
 * @Version 1.0
 */
@Service
@BaseServiceAnnotation
public class ShopSpuServiceImpl extends BaseServiceImpl<ShopSpuMapper, ShopSpu, ShopSpuExample> implements IShopSpuService {

    @Autowired
    private ShopSpuCategoryMapper spuCategoryMapper;

    @Override
    public List<EasyUITreeNode> getSpuCatList(Long parentId) {
        List<ShopSpuCategory> shopSpuCategories = spuCategoryMapper.selectListByParentId(parentId);

        List<EasyUITreeNode> result = new ArrayList<>();
        for (ShopSpuCategory spuCategory: shopSpuCategories) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(spuCategory.getCatId());
            node.setState(spuCategory.getIsParent()?"closed":"open");
            node.setText(spuCategory.getName());
            result.add(node);
        }
        return result;
    }
}
