package com.kowa.app.controller;

import com.kowa.app.aop.LoginHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面路由
 * @Auther yumengshuai【kely】
 * @Date   17/7/4 下午3:56
 *
 */
@Controller
public class PageRouter {

    @RequestMapping("/Test")
    public String home(Model model) {
        return "/page/test";
    }


    @RequestMapping("/app")
    public String app(Model model) {
        return "/page/app";
    }
}
