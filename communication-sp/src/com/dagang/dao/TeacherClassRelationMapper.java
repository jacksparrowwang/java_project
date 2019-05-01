package com.dagang.dao;

import com.dagang.model.TeacherClassRelation;
import org.springframework.stereotype.Repository;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

@Repository
public interface TeacherClassRelationMapper {

    public int insertClassTeaRelat(TeacherClassRelation teacherClassRelation);
}
