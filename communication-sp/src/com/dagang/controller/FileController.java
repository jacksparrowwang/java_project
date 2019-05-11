package com.dagang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther wangchenggang
 * @Date 2019/5/7 14:59
 */

@Controller
public class FileController {

    @RequestMapping()
    public String fileShow() {
        return "";
    }
}
