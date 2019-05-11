package com.dagang.service;


import com.dagang.Util.EventUtil;
import com.dagang.Util.MarkUtil;
import com.dagang.dao.StudentParentMapper;
import com.dagang.model.StudentParent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

@Service
public class StudentPServiceImpl implements StudentPService{

    private static Logger log = Logger.getLogger(StudentPServiceImpl.class);

    @Autowired
    private StudentParentMapper studentParentMapper;

    @Override
    public boolean createStudentP(StudentParent studentParent) {
        if (studentParent == null) {
            log.error("createStudentP:parameter is null");
            return false;
        }
        if (isExist(studentParent.getParentPhoneNumber())) {
            log.info("user is exist");
            return false;
        }
        // 生成uid
        studentParent.setDatetime(System.currentTimeMillis());
        studentParent.setClassId(-1);
        studentParent.setIdType(MarkUtil.STUDENTPARENT);
        studentParent.setParUid(makeUid(studentParent.getParentPhoneNumber()));

        int result = studentParentMapper.insert(studentParent);
        if (result == 0) {
            log.error("student login insert error");
            return false;
        }
        return true;
    }

    private int makeUid(String number) {
        String num = number.substring(number.length()-4,number.length());
        return EventUtil.productUid()* 10000 + Integer.parseInt(num);
    }

    @Override
    public boolean isExist(String number) {
        if (number.isEmpty() || number == null) {
            log.error("isExist:student phone number is null");
            return true;
        }
        List<StudentParent> studentParents = studentParentMapper.findStudentPByPhoneNumber(number);
        if (studentParents.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isStudentP() {
        return false;
    }

    @Override
    public boolean login(String parentPhoneNumber, String password) {

        if (parentPhoneNumber.isEmpty() || password.isEmpty()) {
            return false;
        }

        StudentParent studentParent = studentParentMapper.login(parentPhoneNumber,password);
        if (studentParent == null) {
            return false;
        }
        return true;
    }

    @Override
    public StudentParent queryStuByPhoneAndPass(String parentPhoneNumber, String password) {
        StudentParent studentParent = studentParentMapper.login(parentPhoneNumber,password);
        if (studentParent == null) {
            return null;
        }
        return studentParent;
    }

    @Override
    public String getUserNameByPhoneNu(String parentPhoneNumber) {
        if (parentPhoneNumber == null || parentPhoneNumber.isEmpty()) {
            return null;
        }
        String[] usernames =  studentParentMapper.queryUserNameByPN(parentPhoneNumber);
        if (usernames.length > 1) {
            System.out.println("学生表错误，产生了两个相同的手机号");
            return null;
        }
        return usernames[0];
    }
}
