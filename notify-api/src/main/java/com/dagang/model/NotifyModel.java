package com.dagang.model;

import java.io.Serializable;

/**
 * @auther wangchenggang
 * @Date 2019/5/29 18:40
 */

public class NotifyModel implements Serializable{

    private Long id;

    private Long time_eventID;

    private String event_message;

    private Integer classId;

    private String teacherName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime_eventID() {
        return time_eventID;
    }

    public void setTime_eventID(Long time_eventID) {
        this.time_eventID = time_eventID;
    }

    public String getEvent_message() {
        return event_message;
    }

    public void setEvent_message(String event_message) {
        this.event_message = event_message;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public NotifyModel() {
    }

    public NotifyModel(String event_message, Integer classId, String teacherName) {
        this.event_message = event_message;
        this.classId = classId;
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "NotifyModel{" +
                "id=" + id +
                ", time_eventID=" + time_eventID +
                ", event_message='" + event_message + '\'' +
                ", classId=" + classId +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
