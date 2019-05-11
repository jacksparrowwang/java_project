package com.dagang.service;

import com.dagang.model.Teacher;
import org.springframework.stereotype.Service;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */


public interface TeacherService {

    public boolean createTeacher(Teacher teacher);

    public boolean isTeacher();

    // 判断是否有已经有人用phoneNumber注册过了
    public boolean isExist(Teacher teacher) throws Exception;

    public String getUserNameByPhoneNumber(String tPhoneNumber);

    // 登陆时候进行判断是否登陆成功
    public boolean login(String phoneNumber, String password);

    public Teacher queryTeacherByPhonAndPass(String phoneNumber, String password);

}
