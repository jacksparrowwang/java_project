package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/4/18 18:22
 */

public class Teacher {

    private Integer id;

    private Long dateTime;

    /*老师唯一的标识id*/
    private Integer tUid;

    /*老师的名字*/
    private String teaName;

    /*老师的电话*/
    private String tPhoneNumber;

    /*密码*/
    private String password;

    /*性别*/
    private Integer gender;

    /*老师带的课或者班主任*/
    private String role;

    /*是否是老师，1为老师 0为学生*/
    private Integer idType;

    /*老师的描述*/
    private String tDesc;

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettUid() {
        return tUid;
    }

    public void settUid(Integer tUid) {
        this.tUid = tUid;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String gettPhoneNumber() {
        return tPhoneNumber;
    }

    public void settPhoneNumber(String tPhoneNumber) {
        this.tPhoneNumber = tPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", tUid=" + tUid +
                ", teaName='" + teaName + '\'' +
                ", tPhoneNumber='" + tPhoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", role='" + role + '\'' +
                ", idType=" + idType +
                ", tDesc='" + tDesc + '\'' +
                '}';
    }
}
