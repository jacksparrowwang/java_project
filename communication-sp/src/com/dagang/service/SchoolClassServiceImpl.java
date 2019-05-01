package com.dagang.service;

import com.dagang.dao.SchoolClassMapper;
import com.dagang.model.SchoolClass;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService{

    @Autowired
    private SchoolClassMapper schoolClassMapper;

    @Override
    public boolean createClass(SchoolClass schoolClass) {
        System.out.println(schoolClass.toString());
        int result = schoolClassMapper.insert(schoolClass);
        if (result == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String queryClassInfo(String queryAddress, String querySchool, String queryClass) {
        List<SchoolClass> schoolClasses = schoolClassMapper.queryClassInfo(queryAddress,querySchool,queryClass);
        System.out.println("数据库查询结果"+ schoolClasses);
        if (schoolClasses == null) {
            // 没有要查询的班级
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(schoolClasses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public boolean isExistClass(String schoolAddress, String schoolName, String className) {
        if (schoolAddress == null || schoolAddress.isEmpty()
                || schoolName == null || schoolName.isEmpty()
                || className == null || className.isEmpty()) {
            // 这里就是个异常了
        }
        List<Integer> result = schoolClassMapper.isExistClass(schoolAddress,schoolName,className);
        System.out.println("result"+result);
        if (result == null || result.isEmpty()) {
            // false 表示不存在该班级
            return false;
        }
        // 存在该班级
        return true;
    }
}
