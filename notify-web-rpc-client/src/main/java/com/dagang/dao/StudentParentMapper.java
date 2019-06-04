package com.dagang.dao;

import com.dagang.model.StudentParent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentParentMapper {

    public int insert(StudentParent studentParent);

    public List<StudentParent> findStudentPByPhoneNumber(String parentPhoneNumber);

    public StudentParent login(@Param("parentPhoneNumber") String parentPhoneNumber, @Param("password") String password);

    public String[] queryUserNameByPN(String parentPhoneNumber);

    public int setClassId(@Param("parentPhoneNumber") String parentPhoneNumber,
                          @Param("studentName") String studentName,
                          @Param("classId") Integer classId);

    public String[] queryUserNameByClassId(Integer classId);

    public String queryNotifyEventByPhone(String parentPhoneNumber);

}
