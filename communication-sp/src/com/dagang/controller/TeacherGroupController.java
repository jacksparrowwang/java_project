package com.dagang.controller;

import com.dagang.service.TeaClaRelationService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * @auther wangchenggang
 * @Date 2019/5/7 17:57
 */

@Controller
public class TeacherGroupController {

    @Autowired
    TeaClaRelationService teaClaRelationService;

    @RequestMapping("/teacherChat")
    public String teacherShow() {
        return "chatgroup";
    }
    @RequestMapping(value = "/getClassAndName", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getClassAndName(HttpServletRequest request) {
        String phoneNumber = (String) request.getSession().getAttribute("user");
        Map<Integer,String> map = teaClaRelationService.queryClassIdAndClassNameByPhone(phoneNumber);
        if (map == null || map.isEmpty()) {
            System.out.println("getClassAndName:该老师所没有所对应的班级");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(map);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
