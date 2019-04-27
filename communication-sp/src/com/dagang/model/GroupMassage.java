package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/4/24 17:26
 */

public class GroupMassage {
    private Long id;
    private Long date;

    // 班级Id
    private Integer classId;

    // 消息内容
    private String massage;

    // 发送者Id
    private Integer sendId;

    // 发送者姓名
    private String sendName;

    // 发送者身份是谁
    private Integer sendType;

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

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
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
        return "GroupMassage{" +
                "id=" + id +
                ", date=" + date +
                ", classId=" + classId +
                ", massage='" + massage + '\'' +
                ", sendId=" + sendId +
                ", sendName='" + sendName + '\'' +
                ", sendType=" + sendType +
                '}';
    }
}
