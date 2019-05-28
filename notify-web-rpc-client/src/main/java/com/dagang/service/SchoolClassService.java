package com.dagang.service;

import com.dagang.model.SchoolClass;

public interface SchoolClassService {
    public boolean createClass(SchoolClass schoolClass);

    // 查询班级信息
    public String queryClassInfo(String queryAddress, String querySchool, String queryClass);

    public boolean isExistClass(String schoolAddress, String schoolName, String className);

    public String findClassNameById(Integer classId);
}
