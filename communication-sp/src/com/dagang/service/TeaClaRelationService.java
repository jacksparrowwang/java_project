package com.dagang.service;

import com.dagang.model.SchoolClass;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

public interface TeaClaRelationService {
    // 这是用来进行添加老师和班级关系，可能存在多对多的关系
    public boolean establishTeacherAndClassRelationships(String tPhoneNumber, String classId);

    // 当老师创建了班级就会进行一次自动的绑定班级
    public boolean autoBindClass(SchoolClass schoolClass, String tPhoneNumber);
}