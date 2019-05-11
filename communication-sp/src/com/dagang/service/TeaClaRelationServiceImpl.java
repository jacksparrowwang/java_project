package com.dagang.service;

import com.dagang.dao.TeacherClassRelationMapper;
import com.dagang.dao.TeacherMapper;
import com.dagang.model.SchoolClass;
import com.dagang.model.Teacher;
import com.dagang.model.TeacherClassRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public boolean isExitOfCorrespondence(Integer tuid) {

        List<Integer> list = teacherClassRelationMapper.queryClassIdByTuid(tuid);

        if (list == null || list.isEmpty()) {
            return false;
        }
        // 有返回true, 没有对应的关系返回false
        return true;
    }

    @Override
    public Map<Integer, String> queryClassIdAndClassNameByPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            System.out.println("queryClassIdAndClassNameByPhone : parameter is null" + phone);
            return null;
        }
        List<TeacherClassRelation> list = teacherClassRelationMapper.queryClassIDAndNameByPhone(phone);
        System.out.println("哈哈哈哈哈哈"+list);
        if (list == null || list.isEmpty()) {
            return null;
        }
        Map<Integer,String> newMap = new HashMap();
        for (TeacherClassRelation t : list) {
            newMap.put(t.getClassId(), t.getClassName());
        }
        System.out.println("ahahhahahahahhahdasfkasdfscccdf"+newMap);
        return newMap;
    }
}
