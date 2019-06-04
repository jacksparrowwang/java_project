package com.dagang.controller;

import com.dagang.model.StudentParent;
import com.dagang.service.StudentPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther wangchenggang
 * @Date 2019/4/27 13:46
 */
@Controller
public class StudentPRegisterController {
    @Autowired
    StudentPService studentPService;

    @RequestMapping("/studentRegister")
    public String studentRegister(HttpServletRequest request) {

        String studentName =  request.getParameter("studentName");
        String parentPhoneNumber =  request.getParameter("parentPhoneNumber");
        String password =  request.getParameter("password");
        String gender =  request.getParameter("gender");
        String studentDesc =  request.getParameter("studentDesc");

        StudentParent studentParent = new StudentParent();
        studentParent.setStudentName(studentName);
        studentParent.setParentPhoneNumber(parentPhoneNumber);
        studentParent.setPassword(password);
        studentParent.setGender(Integer.parseInt(gender));
        studentParent.setStudentDesc(studentDesc);
        studentParent.setNotify_event("0"); // 用字符0标识无事件

        boolean result = studentPService.createStudentP(studentParent);
        if (result) {
            return "login";
        }
        return "registerFailed";
    }
}
