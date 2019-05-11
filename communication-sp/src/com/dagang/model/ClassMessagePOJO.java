package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/5/11 16:38
 */

public class ClassMessagePOJO {
    private Integer classId;

    private String message;

    private String phoneNumber;

    private Integer iden;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }
}
