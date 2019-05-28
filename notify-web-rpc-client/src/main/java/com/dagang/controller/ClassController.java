package com.dagang.controller;

import com.dagang.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther wangchenggang
 * @Date 2019/4/26 10:23
 */

@Controller
public class ClassController {

    @Autowired
    SchoolClassService schoolClassService;

    @RequestMapping(value = "/queryClass")
    public void queryClass(HttpServletRequest request, HttpServletResponse response) {
        String queryAddress = request.getParameter("queryAddress");
        String querySchool = request.getParameter("querySchool");
        String queryClass = request.getParameter("queryClass");

        System.out.println("queryAddress"+queryAddress);
        System.out.println("querySchool"+querySchool);
        System.out.println("queryClass"+queryClass);

        String json = schoolClassService.queryClassInfo(queryAddress, querySchool, queryClass);
        System.out.println("json"+json);
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findClassId")
    public String findClassId() {
        return "findClassId";
    }

}
