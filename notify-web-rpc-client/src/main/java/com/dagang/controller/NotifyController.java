package com.dagang.controller;

import com.dagang.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther wangchenggang
 * @Date 2019/5/26 20:41
 */

@Controller
public class NotifyController {

    @Autowired
    NotifyService notifyService;

    @RequestMapping("/work")
    public @ResponseBody String init() {
        boolean f = notifyService.notifyWorks(1);
        System.out.println(f);
        return "1";
    }
}
