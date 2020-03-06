package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class pageController {
    /**
     * 跳转到开发者登录页面
     * @return
     */
    @RequestMapping("/devlogin")
    public String todevlogin(){
        return "/devlogin";
    }


}
