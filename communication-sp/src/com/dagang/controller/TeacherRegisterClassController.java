package com.dagang.controller;

import com.dagang.Util.EventUtil;
import com.dagang.model.SchoolClass;
import com.dagang.model.Teacher;
import com.dagang.service.SchoolClassService;
import com.dagang.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther wangchenggang
 * @Date 2019/4/18 21:26
 */


/*
 进行页面的刷新
 response.setHeader("refresh", "时间")
 等待多久就跳到某个页面
 response.setHeader("refresh", "时间;url=xxx")
*/

@Controller
public class TeacherRegisterClassController {
    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/registerClass")
    public String registerClass(HttpServletRequest request) {
        if (EventUtil.isLoginAndTeacher(request)) {
            return "createClass";
        }
        // 这里是没有登陆或者注册时候
        return "login";
    }

    @RequestMapping("/isExistClass")
    public void isExistClass(HttpServletRequest request, HttpServletResponse response) {
        String schoolAddress = request.getParameter("schoolAddress");
        String schoolName = request.getParameter("schoolName");
        String className = request.getParameter("className");
        try {
            if (schoolAddress == null || schoolAddress.isEmpty()
                    || "请选择省份请选择市区".equals(schoolAddress)
                    || schoolName == null || schoolName.isEmpty()
                    || className == null || className.isEmpty()) {
                //"请选择地址和填写创建信息"错误代码为1
                response.getWriter().write(1);
                return;
            }
            //"该班级存在"错误代码为2
            if (schoolClassService.isExistClass(schoolAddress,schoolName,className)) {
                response.getWriter().write(2);
                return;
            }
            // OK 正确代码为3
            response.getWriter().write(3);
        } catch (IOException e) {
            System.out.println("TeacherRegisterClassController:判断班级是否存在错误");
            e.printStackTrace();
        }
    }

    @RequestMapping("/createClass")
    public void createClass(HttpServletRequest request, HttpServletResponse response) {
        String schoolAddress = request.getParameter("schoolAddress");
        String schoolName = request.getParameter("schoolName");
        String className = request.getParameter("className");
        String classDesc = request.getParameter("classDesc");

        String phoneNumber = (String) request.getSession().getAttribute("user");
        String password = (String) request.getSession().getAttribute("pass");
        String iden = (String) request.getSession().getAttribute("iden");
        try {
            if (phoneNumber == null || phoneNumber.isEmpty() || password == null || password.isEmpty()) {
                // 未填写信息
                response.getWriter().write(1);
            }
            int idtype = Integer.parseInt(iden);
            if (idtype == 0) {
                // 不是老师身份
                response.getWriter().write(2);
            } else if (idtype == 1) {
                // 是老师,拿到创建人信息
                Teacher teacher = teacherService.queryTeacherByPhonAndPass(phoneNumber, password);
                // 组装班级model
                SchoolClass schoolClass = new SchoolClass();
                schoolClass.setSchoolAddress(schoolAddress);
                schoolClass.setSchoolName(schoolName);
                schoolClass.setClassName(className);
                schoolClass.setClassDesc(classDesc);
                schoolClass.setDateTime(System.currentTimeMillis());
                // 用老师的手机后四位与随机数进行组合成
                schoolClass.setClassId(makeUid(teacher.gettPhoneNumber()));
                schoolClass.setCreateTUid(teacher.gettUid());
                schoolClass.setCreatePhoneNumber(teacher.gettPhoneNumber());

                if (schoolClassService.createClass(schoolClass)) {
                    // 创建成功
                    response.getWriter().write(3);
                }
                // 创建失败
                response.getWriter().write(4);
            } else {
                // 错误情况
                response.getWriter().write(5);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int makeUid(String number) {
        String num = number.substring(number.length() - 4, number.length());
        return EventUtil.productUid() * 10000 + Integer.parseInt(num);
    }
}