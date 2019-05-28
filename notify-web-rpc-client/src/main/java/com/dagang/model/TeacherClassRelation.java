package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/4/28 18:03
 */

public class TeacherClassRelation {
    private Integer id;

    private Long dateTime;

    // 代课老师tUid
    private Integer tUid;

    // 班级id
    private Integer classId;

    // 老师所代课，自己的身份
    private String role;

    // 代课老师姓名
    private String teaName;

    // 代课老师的电话
    private String tPhoneNumber;

    // 班级名字
    private String className;

    // 班级创建人uid
    private Integer createTUid;

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

    public Integer gettUid() {
        return tUid;
    }

    public void settUid(Integer tUid) {
        this.tUid = tUid;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCreateTUid() {
        return createTUid;
    }

    public void setCreateTUid(Integer createTUid) {
        this.createTUid = createTUid;
    }

    @Override
    public String toString() {
        return "TeacherClassRelation{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", tUid=" + tUid +
                ", classId=" + classId +
                ", role='" + role + '\'' +
                ", teaName='" + teaName + '\'' +
                ", tPhoneNumber='" + tPhoneNumber + '\'' +
                ", className='" + className + '\'' +
                ", createTUid=" + createTUid +
                '}';
    }
}
