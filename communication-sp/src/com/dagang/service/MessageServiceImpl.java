package com.dagang.service;

import com.dagang.dao.GroupMessagesqlMapper;
import com.dagang.dao.StudentParentMapper;
import com.dagang.dao.TeacherMapper;
import com.dagang.model.ClassMessagePOJO;
import com.dagang.model.GroupMessagesql;
import com.dagang.model.StudentParent;
import com.dagang.model.Teacher;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/8 20:56
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private GroupMessagesqlMapper groupMessagesqlMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentParentMapper studentParentMapper;

    @Override
    public List<GroupMessagesql> queryMessageContentByClassId(Integer classId) {
        if (classId == null) {
            System.out.println("MessageServiceImpl : queryMessageContentByClassId : param is null");
            return null;
        }

        List<GroupMessagesql> result = groupMessagesqlMapper.queryMessageContentByClassId(classId);
        System.out.println("测试拿到的群消息数据"+result);
        return result;
    }

    @Override
    public GroupMessagesql sendGroupMessage(ClassMessagePOJO classMessagePOJO) {
        if (classMessagePOJO == null) {
            System.out.println("MessageServiceImpl:sendGroupMessage: param is null");
            return null;
        }

        GroupMessagesql groupMessagesql = new GroupMessagesql();
        groupMessagesql.setClassId(classMessagePOJO.getClassId());
        groupMessagesql.setMessage(classMessagePOJO.getMessage());
        groupMessagesql.setSendPhone(classMessagePOJO.getPhoneNumber());
        groupMessagesql.setDate(System.currentTimeMillis());

        List<Teacher> teachers = null;
        List<StudentParent> studentParents = null;

        if (classMessagePOJO.getIden() == 1) {
            // 老师
            teachers = teacherMapper.findTeacherByTPhoneNumber(classMessagePOJO.getPhoneNumber());
            if (teachers == null || teachers.isEmpty()) {
                System.out.println("MessageServiceImpl:sendGroupMessage: teachers is null");
                return null;
            }
            groupMessagesql.setSendId(teachers.get(0).gettUid());
            groupMessagesql.setSendName(teachers.get(0).getTeaName());
            groupMessagesql.setRole(teachers.get(0).getRole());
            groupMessagesql.setSendType(teachers.get(0).getIdType());
        } else if (classMessagePOJO.getIden() == 0) {
            // 学生
            studentParents = studentParentMapper.findStudentPByPhoneNumber(classMessagePOJO.getPhoneNumber());
            if (studentParents == null || studentParents.isEmpty()) {
                System.out.println("MessageServiceImpl:sendGroupMessage: studentParents is null");
                return null;
            }
            groupMessagesql.setSendId(studentParents.get(0).getParUid());
            groupMessagesql.setSendName(studentParents.get(0).getStudentName());
            groupMessagesql.setRole(null);
            groupMessagesql.setSendType(studentParents.get(0).getIdType());
        } else {
            // 错误
            System.out.println("MessageServiceImpl:sendGroupMessage: iden error");
            return null;
        }
        // 进行插入数据库
        if (!groupMessagesqlMapper.insertGroupMessage(groupMessagesql)) {
            // 插入失败
            System.out.println("MessageServiceImpl:sendGroupMessage: insert message failed");
            return null;
        }
        // 插入成功
        return groupMessagesql;
    }
}
