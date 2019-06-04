package com.dagang.service;

import com.dagang.model.AcceptAndNot;
import com.dagang.model.NotifyModel;
import com.dagang.model.StudentSearchModel;

import java.util.List;
import java.util.Map;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

public interface NotifyService {

    /**
     *  老师通知学生事件
     * @param notifyModel 通知model
     * @return  是否成功
     */
    public boolean notifyWorks(NotifyModel notifyModel);

    /**
     *  查询通知的事件
     * @param classId
     * @return
     */
    public List<NotifyModel> queryNotifyEventByClassId(Integer classId);

    /**
     *  查看学生是否收到同时，并进行统计收到或者未收到的
     * @param eventID 事件Id
     * @param classId 班级Id
     * @return  返回的是否确认的数组
     */
    public List<AcceptAndNot> minuteAcceptInfo(String eventID, Integer classId);

    /**
     *  学生端查询到事件并且比较自己是否确认
     * @param phone 学生标识
     * @param classId 班级Id
     * @return 返回的是事件数据包
     */
    public StudentSearchModel studentSearchEventInfo(String phone, Integer classId);

    /**
     *  学生进行确认收到通知
     * @param phoneNumber 学生标识
     * @param eventId 通知的事件
     * @return 是否确认成功
     */
    public boolean acceptNotifyOK(String phoneNumber, Long eventId);
}
