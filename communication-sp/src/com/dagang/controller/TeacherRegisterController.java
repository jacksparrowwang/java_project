package com.dagang.controller;

import com.dagang.Util.EventUtil;
import com.dagang.model.Teacher;
import com.dagang.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther wangchenggang
 * @Date 2019/4/27 13:45
 */

@Controller
public class TeacherRegisterController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/teachertRegister")
    public String teacherRegister(HttpServletRequest request, HttpServletResponse response) {
        // 这里要进行判断是否老师注册成功
        //System.out.println(request.toString());
        String teaName = request.getParameter("teaName");
        String tPhoneNumber = request.getParameter("tPhoneNumber");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String gender = request.getParameter("gender");
        String tDesc = request.getParameter("tDesc");

        // 对传过来的参数进行处理
        Teacher teacher = new Teacher();
        teacher.setTeaName(teaName.trim());
        teacher.settPhoneNumber(tPhoneNumber.trim());
        teacher.setPassword(password);
        teacher.setRole(role.trim());
        teacher.setGender(Integer.parseInt(gender));
        teacher.settDesc(tDesc.trim());

        System.out.println(teacher.toString());
        boolean result = teacherService.createTeacher(teacher);
        if (result) {
            // TODO
            // 设置cookie和session
            EventUtil.setCookieAndSession(request,response,tPhoneNumber,password,"1");
            // 老师注册成功后就需要进行选择班级，如果没有就进行创建班级，学生是在最后由老师进行班级的选择
            return "registerSuccessTeacher"; // 这里返回页面需要修改
        }
        return "registerFailed";
    }

    @RequestMapping("/createClassInfo")
    public String teacherCreateClass(HttpServletRequest request) {
        // 加上判断，是否为登陆状态和老师身份
        if (!EventUtil.isLoginAndTeacher(request)) {
            return "login";
        }
        return "findClassId";
    }
}
