package com.fuyi.shop.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description 首页控制器
 * @Author fuyi
 * @Date 2019/1/8 16:30
 * @Version 1.0
 */
@Controller
public class PageController {

    /**
     * 显示首页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "/index";
    }

    /**
     * 根据访问路径url,展示相应页面
     * @param url
     * @return
     */
    @RequestMapping("/{url}")
    public String showPage(@PathVariable String url) {
        return url;
    }
}