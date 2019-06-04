package com.dagang.servicedao;

import com.dagang.servicemodel.NotifyStudentMode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

@Repository
public interface NotifyStudentParentsMapper {

    /**
     *  拉取本班级的通知的事件和学生的姓名
     * @param classId 班级的Id
     * @return  返回的每个同学姓名和事件
     */
    public List<Map<String, String>> queryNameMemberByClassId(Integer classId);


    public List<NotifyStudentMode> queryUidMemberByClassId(Integer classId);


    public int setNotifyEventByClassId(@Param("parUid") Integer parUid, @Param("notify_event") String notify_event);

    public String queryEventOfOwnByPhone(String phone);

    public boolean setEventOfOK(@Param("notify_event")String notify_event, @Param("parentPhoneNumber")String parentPhoneNumber);

}
