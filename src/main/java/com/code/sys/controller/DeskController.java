package com.code.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页控制器
 */
@Controller
@RequestMapping("desk")
public class DeskController {

    /**
     * 跳转到后台首页的页面
     * @return
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/main/deskManger";
    }
}
