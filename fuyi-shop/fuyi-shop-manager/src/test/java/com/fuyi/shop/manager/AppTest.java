package com.fuyi.shop.manager;

import com.fuyi.shop.manager.entity.ShopItemCat;
import com.fuyi.shop.manager.service.IShopItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName AppTest
 * @Description TODO
 * @Author fuyi
 * @Date 2018/12/17 16:51
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopManagerApplication.class)
public class AppTest {

    @Autowired
    IShopItemService shopItemService;

    @Test
    public void test1() {
        List<ShopItemCat> itemCatList = shopItemService.getItemCatListByParentId(0L);
        System.out.print(itemCatList);
    }

}
