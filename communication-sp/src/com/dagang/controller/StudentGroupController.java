package com.dagang.controller;

import com.dagang.model.ClassMessagePOJO;
import com.dagang.service.SchoolClassService;
import com.dagang.service.StudentPService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @auther wangchenggang
 * @Date 2019/5/14 17:44
 */

@Controller
public class StudentGroupController {

    @Autowired
    private StudentPService studentPService;

    @Autowired
    private SchoolClassService schoolClassService;

    private ObjectMapper o = new ObjectMapper();

    @RequestMapping(value = "/getClassMember",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getClassMember(HttpServletRequest request) {
        String cl = request.getQueryString().split("=")[1];
        System.out.println(cl);
        Integer classId = Integer.parseInt(cl);

        String[] strings = studentPService.getClassMember(classId);

        String result = null;
        try {
            result = o.writeValueAsString(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getClassNameById",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getClassNameById(HttpServletRequest request) {
        System.out.println(request.getQueryString());
        String cl = request.getQueryString().split("=")[1];
        Integer classId = Integer.parseInt(cl);

        String className = schoolClassService.findClassNameById(classId);
        System.out.println("className"+className);
        String result = null;
        try {
            result = o.writeValueAsString(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
