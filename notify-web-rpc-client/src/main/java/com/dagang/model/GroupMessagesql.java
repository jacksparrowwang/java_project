package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/5/7 16:09
 */

public class GroupMessagesql {
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

    // 发送人电话
    private String sendPhone;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
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
        return "GroupMessagesql{" +
                "id=" + id +
                ", date=" + date +
                ", classId=" + classId +
                ", message='" + message + '\'' +
                ", sendId=" + sendId +
                ", sendName='" + sendName + '\'' +
                ", sendType=" + sendType +
                ", sendPhone='" + sendPhone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
