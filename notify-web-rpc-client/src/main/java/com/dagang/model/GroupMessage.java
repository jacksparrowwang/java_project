package com.dagang.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @auther wangchenggang
 * @Date 2019/4/24 17:26
 */

public class GroupMessage {
    private Long id;
    private Long date;

    // 班级Id
    private Integer classId;

    // 消息内容
    private String message;

    // 发送者Id
    private Integer sendId;

    // 发送者姓名
    private String sendName;

    // 发送者身份是谁
    private Integer sendType;

    private Date dateF;

    private Map<String, String> onlineName;


    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public Map<String, String> getOnlineName() {
        return onlineName;
    }

    public void setOnlineName(Map<String, String> onlineName) {
        this.onlineName = onlineName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMassage(String name,String msg) {
        this.message = name+" "+ DateFormat.getDateTimeInstance().format(new Date()) +":<br/> "+msg;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    @Override
    public String toString() {
        return "GroupMessage{" +
                "id=" + id +
                ", date=" + date +
                ", classId=" + classId +
                ", message='" + message + '\'' +
                ", sendId=" + sendId +
                ", sendName='" + sendName + '\'' +
                ", sendType=" + sendType +
                ", onlineName=" + onlineName +
                '}';
    }
}
