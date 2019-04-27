package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/4/18 18:26
 */

public class SchoolClass {

    private Integer id;

    private Long dateTime;

    /*班级的唯一标识id*/
    private Integer classId;

    /*创建人uid，一般只有老师才能创建*/
    private Integer createTUid;

    /*班级名字*/
    private String className;

    /*学校名字*/
    private String schoolName;

    /*创建人电话*/
    private String createPhoneNumber;

    /*学校地址*/
    private String schoolAddress;

    /*班级描述*/
    private String classDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCreateTUid() {
        return createTUid;
    }

    public void setCreateTUid(Integer createTUid) {
        this.createTUid = createTUid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCreatePhoneNumber() {
        return createPhoneNumber;
    }

    public void setCreatePhoneNumber(String createPhoneNumber) {
        this.createPhoneNumber = createPhoneNumber;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", classId=" + classId +
                ", createTUid=" + createTUid +
                ", className='" + className + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", createPhoneNumber='" + createPhoneNumber + '\'' +
                ", schoolAddress='" + schoolAddress + '\'' +
                ", classDesc='" + classDesc + '\'' +
                '}';
    }
}
