package com.dagang.controller;

import cn.dsna.util.images.ValidateCode;
import com.dagang.Util.EventUtil;
import com.dagang.model.StudentParent;
import com.dagang.model.Teacher;
import com.dagang.service.StudentPService;
import com.dagang.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther wangchenggang
 * @Date 2019/4/19 20:24
 */


@Controller
public class LoginController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentPService studentPService;

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    // 接收登陆过来的参数
    @RequestMapping("/communication")
    public String Communication(HttpServletRequest request, HttpServletResponse response) {
        String phoneNumber = request.getParameter("phoneNumber").trim();
        String password = request.getParameter("password").trim();
        String identity = request.getParameter("identity").trim();
        String vali = request.getParameter("clientCode").trim();
        String validateCode = (String) request.getSession().getAttribute("validateCode");
        if (!vali.equalsIgnoreCase(validateCode)) {
            return "registerFailed";
        }
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            // TODO
            return "registerFailed";
        }
        int iden = Integer.parseInt(identity);
        if (iden == 0) {
            // 学生家长
            StudentParent studentParent = studentPService.queryStuByPhoneAndPass(phoneNumber, password);
            if (studentParent == null) {
                return "registerFailed";
            }
            request.setAttribute("username",studentParent.getStudentName());
        } else if (iden == 1) {
            // 老师
            Teacher teacher = teacherService.queryTeacherByPhonAndPass(phoneNumber,password);
            if (teacher == null) {
                return "registerFailed";
            }
            request.setAttribute("username",teacher.getTeaName());
        } else {
            // 出现错误
            return "500";
        }
        // 设置session
       EventUtil.setCookieAndSession(request,response,phoneNumber,password,identity);
        // 登陆成功
        return "communication";
    }

    @RequestMapping("/register")
    public String registerInfoWrite() {
        return "registerTeacherAndStudent";
    }

    @RequestMapping("/success")
    public String registerSuccess() {
        return "registerSuccessTeacher";
    }

    @RequestMapping("/failed")
    public String registerFailed() {
        return "registerFailed";
    }

    // 验证码
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) {
        ValidateCode validateCode = new ValidateCode(120,30,4,4);
        request.getSession().setAttribute("validateCode",validateCode.getCode());
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
