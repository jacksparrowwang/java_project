package com.dagang.servicemodel;

/**
 * @auther wangchenggang
 * @Date 2019/5/31 12:00
 */

public class NotifyStudentMode {
    private Integer parUid;

    private String studentName;

    private String notify_event;

    public Integer getParUid() {
        return parUid;
    }

    public void setParUid(Integer parUid) {
        this.parUid = parUid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNotify_event() {
        return notify_event;
    }

    public void setNotify_event(String notify_event) {
        this.notify_event = notify_event;
    }

    @Override
    public String toString() {
        return "NotifyStudentMode{" +
                "parUid=" + parUid +
                ", studentName='" + studentName + '\'' +
                ", notify_event='" + notify_event + '\'' +
                '}';
    }
}
