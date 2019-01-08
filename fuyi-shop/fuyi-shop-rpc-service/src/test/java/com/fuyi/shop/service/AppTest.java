package com.fuyi.shop.service;

import com.fuyi.shop.rpc.ShopServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static com.alibaba.boot.dubbo.util.DubboUtils.BASE_PACKAGES_PROPERTY_NAME;

/**
 * @ClassName AppTest
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/4 20:00
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopServiceApplication.class)
public class AppTest {

//    @Reference(version = "1.0.0",
//            application = "${dubbo.application.id}",
//            url = "dubbo://localhost:12345")
//    private IShopItemService shopItemService;

    @Autowired
    Environment environment;

    @Test
    public void test1() {
        System.out.println("==============" +BASE_PACKAGES_PROPERTY_NAME);
        System.out.println("==============" + environment.getProperty(BASE_PACKAGES_PROPERTY_NAME));
    }

}
