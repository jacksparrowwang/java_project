package com.dagang.controller;

import cn.dsna.util.images.ValidateCode;
import com.dagang.Util.EventUtil;
import com.dagang.model.StudentParent;
import com.dagang.model.Teacher;
import com.dagang.service.StudentPService;
import com.dagang.service.TeaClaRelationService;
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

    @Autowired
    private TeaClaRelationService teaClaRelationService;

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
            return "loginFailed";
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
                return "loginFailed";
            }
            // 已经登陆成功，进行设置session
            request.getSession().setAttribute("username",studentParent.getStudentName());
            EventUtil.setCookieAndSession(request,response,phoneNumber,password,identity);

            // 判断是否自己在班级内，也就是判断classId是否为-1
            try {
                if (!isExistClassOfStudent(studentParent)) {
                    return "notifyTeacherAdd";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (iden == 1) {
            // 老师
            Teacher teacher = teacherService.queryTeacherByPhonAndPass(phoneNumber,password);
            if (teacher == null) {
                return "loginFailed";
            }
            // 已经登陆成功，进行设置session
            request.getSession().setAttribute("username",teacher.getTeaName());
            EventUtil.setCookieAndSession(request,response,phoneNumber,password,identity);

            // 查询老师和班级的关系表，看其中是都有老师所对应的班级，没有跳转至添加和创建页面
            try {
                if (!isExistClassOfTeacher(teacher)) {
                    return "notifyTeacherCreateOrAdd";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 出现错误
            return "500";
        }
        // 登陆成功
        return "chatgroup";
    }

    // 判断学生是否有所对应的班级
    private boolean isExistClassOfStudent(StudentParent studentParent) throws Exception {
        if (studentParent == null) {
            throw new Exception("studentParent error");
        }
        if (studentParent.getClassId() == -1) {
            return false;
        }
        return true;
    }

    // 判断老师是否有对应的班级
    private boolean isExistClassOfTeacher(Teacher teacher) throws Exception {
        if (teacher == null) {
           throw new Exception("error :teacher is null");
        }
        if (!teaClaRelationService.isExitOfCorrespondence(teacher.gettUid())) {
            return false;
        }
        return true;
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
