package com.dagang.dao;

import com.dagang.model.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TeacherMapper {
    public int insert(Teacher teacher);

    public List<Teacher> findTeacherByTPhoneNumber(String tPhoneNumber);

    // 这个是登陆用的，但是也可以进行查询这个人的信息，通过返回值来获取
    public Teacher login(@Param("tPhoneNumber")String tPhoneNumber, @Param("password") String password);

}
