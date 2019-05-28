package com.dagang.service;

import com.dagang.util.EventUtil;
import com.dagang.util.MarkUtil;
import com.dagang.dao.TeacherMapper;
import com.dagang.model.Teacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:22
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    private static Logger log = Logger.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public boolean createTeacher(Teacher teacher) {
        if (teacher == null) {
            log.error("createTeacher:create teacher parameter null");
            return false;
        }

        if (isExist(teacher)) {
            log.error("createTeacher:create teacher exist");
            return false;
        }
        // 设置其他参数
        teacher.setDateTime(System.currentTimeMillis());
        teacher.setIdType(MarkUtil.TEACHER);

        // 生成uid
        teacher.settUid(makeUid(teacher.gettPhoneNumber()));

        int result =  teacherMapper.insert(teacher);
        if (result == 0) {
            log.error("createTeacher:teacher insert error, insert number is" + result);
            return false;
        }
        return true;
    }

    private int makeUid(String number) {
        String num = number.substring(number.length()-4,number.length());
        return EventUtil.productUid()* 10000 + Integer.parseInt(num);
    }

    @Override
    public boolean isTeacher() {
        return false;
    }

    @Override
    public boolean isExist(Teacher teacher) {
        if (teacher == null) {
            log.error("isExist: create teacher parameter null");
        }
        List<Teacher> teacherList =  teacherMapper.findTeacherByTPhoneNumber(teacher.gettPhoneNumber());

        System.out.println(teacherList);

        if (teacherList.isEmpty()) {
            log.info("isExist:user can create");
            return false;
        }
        log.info("isExist:user not create");
        return true;
    }

    @Override
    public String getUserNameByPhoneNumber(String tPhoneNumber) {
        if (tPhoneNumber == null || tPhoneNumber.isEmpty()) {
            System.out.println("参数为null"+tPhoneNumber);
            return null;
        }
        String usernames = teacherMapper.queryUserNameByPhoneNm(tPhoneNumber);

        System.out.println("user+++=++====+++++===++="+usernames);
        return usernames;
    }

    // 判断登陆是否成功
    @Override
    public boolean login(String phoneNumber, String password) {
        Teacher teacher = teacherMapper.login(phoneNumber,password);
        if (teacher == null) {
            return false;
        }
        return true;
    }

    @Override
    public Teacher queryTeacherByPhonAndPass(String phoneNumber, String password) {

        Teacher teacher = teacherMapper.login(phoneNumber,password);
        if (teacher == null) {
            return null;
        }
        return teacher;
    }

}
