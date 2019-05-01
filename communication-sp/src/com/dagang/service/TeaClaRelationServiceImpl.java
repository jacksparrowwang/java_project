package com.dagang.service;

import com.dagang.dao.TeacherClassRelationMapper;
import com.dagang.dao.TeacherMapper;
import com.dagang.model.SchoolClass;
import com.dagang.model.Teacher;
import com.dagang.model.TeacherClassRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/4/28 18:27
 */
@Service
public class TeaClaRelationServiceImpl implements TeaClaRelationService {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TeacherClassRelationMapper teacherClassRelationMapper;

    @Override
    public boolean establishTeacherAndClassRelationships(String tPhoneNumber, String classId) {
        return false;
    }

    @Override
    public boolean autoBindClass(SchoolClass schoolClass, String tPhoneNumber) {
        if (schoolClass == null || tPhoneNumber == null || tPhoneNumber.isEmpty()) {
            // 参数错误
            System.out.println("绑定参数错误");
            return false;
        }
        List<Teacher> teachers = teacherMapper.findTeacherByTPhoneNumber(tPhoneNumber);
        if (teachers == null || teachers.size() != 1) {
            // 数据库已经出现错误
            System.out.println("TeaClaRelationServiceImpl类中的autoBindClass方法；一个tPhoneNumber出现了多个用户，数据库错误");
            return false;
        }
        // 数据的组装
        TeacherClassRelation teacherClassRelation = new TeacherClassRelation();
        teacherClassRelation.setDateTime(System.currentTimeMillis());
        teacherClassRelation.setClassId(schoolClass.getClassId());
        teacherClassRelation.setClassName(schoolClass.getClassName());
        teacherClassRelation.setCreateTUid(teachers.get(0).gettUid());
        teacherClassRelation.setTeaName(teachers.get(0).getTeaName());
        teacherClassRelation.setRole(teachers.get(0).getRole()); // 这是可以修改
        teacherClassRelation.settPhoneNumber(teachers.get(0).gettPhoneNumber());
        teacherClassRelation.settUid(teachers.get(0).gettUid());
        if (teacherClassRelationMapper.insertClassTeaRelat(teacherClassRelation) != 0) {
            return true;
        }
        return false;
    }
}
