package com.fuyi.shop.rpc.api;

import com.fuyi.common.base.BaseService;
import com.fuyi.common.vo.EasyUITreeNode;
import com.fuyi.shop.rpc.entity.ShopSpu;
import com.fuyi.shop.rpc.entity.ShopSpuExample;

import java.util.List;

/**
 * @ClassName IShopSpuService
 * @Description 商品管理service
 * @Author fuyi
 * @Date 2019/1/8 18:19
 * @Version 1.0
 */
public interface IShopSpuService extends BaseService<ShopSpu, ShopSpuExample> {
    /**
     * 获取商品类目
     * @param parentId 父Node id
     * @return
     */
    List<EasyUITreeNode> getSpuCatList(Long parentId);
}
