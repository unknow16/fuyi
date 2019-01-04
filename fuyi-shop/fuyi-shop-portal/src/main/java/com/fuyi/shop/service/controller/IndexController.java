package com.fuyi.shop.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: Administrator
 * @Date: 2019/1/3 00:04
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex() {
        return "home/index";
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "sso/login";
    }

    @RequestMapping(value = "toRegister", method = RequestMethod.GET)
    public String toRegister() {
        return "sso/register";
    }

    @RequestMapping(value = "toItem", method = RequestMethod.GET)
    public String toItem() {
        return "item/item";
    }
}

