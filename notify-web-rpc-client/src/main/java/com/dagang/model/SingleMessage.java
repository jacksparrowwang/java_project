package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/4/24 17:52
 */

public class SingleMessage {
    private Long id;
    private Long date;
    // 发送者id
    private Integer sendId;
    // 接收者id
    private Integer receiveId;
    // 消息内容
    private String massage;
    // 发送者姓名
    private String sendName;
    // 接收者姓名
    private String receiveName;
    // 发送者身份
    private Integer sendType;
    // 接收者身份
    private Integer receiveType;

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

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    @Override
    public String toString() {
        return "SingleMessage{" +
                "id=" + id +
                ", date=" + date +
                ", sendId=" + sendId +
                ", receiveId=" + receiveId +
                ", massage='" + massage + '\'' +
                ", sendName='" + sendName + '\'' +
                ", receiveName='" + receiveName + '\'' +
                ", sendType=" + sendType +
                ", receiveType=" + receiveType +
                '}';
    }
}
