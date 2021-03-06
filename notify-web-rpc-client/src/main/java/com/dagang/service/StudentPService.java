package com.dagang.service;

import com.dagang.model.StudentParent;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

public interface StudentPService {

    public boolean createStudentP(StudentParent studentParent);

    public boolean isExist(String number);

    public boolean isStudentP();

    public boolean login(String parentPhoneNumber, String password);

    public StudentParent queryStuByPhoneAndPass(String parentPhoneNumber, String password);

    public String[] getUserNameByPhoneNu(String parentPhoneNumber);

    public StudentParent findStudentByPhoneNumber(String phoneNumber);

    public boolean setClassIdByPhone(String phone, Integer classId, String userName);
    public boolean setClassIdByPhone(String phone, Integer classId);

    public String[] getClassMember(Integer classId);

    public boolean isExistNewNotify(String phone);
}
