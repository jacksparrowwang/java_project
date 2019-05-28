package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/4/18 18:22
 */

public class StudentParent {

    private Integer id;

    private Long datetime;

    /*学生家长的唯一标识*/
    private Integer parUid;

    /*所在班级的id*/
    private Integer ClassId;

    /*学生父母的电话,也是登陆注册时候的账号*/
    private String parentPhoneNumber;

    /*密码*/
    private String password;

    /*学生的姓名*/
    private String studentName;

    /*学生的性别，0标识女 1标识男 2标识不详*/
    private Integer gender;

    /*是否是老师，1为老师 0为学生*/
    private Integer idType;

    /*学生的描述*/
    private String studentDesc;

    private Integer notifyFlag;

    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public Integer getParUid() {
        return parUid;
    }

    public void setParUid(Integer parUid) {
        this.parUid = parUid;
    }

    public Integer getClassId() {
        return ClassId;
    }

    public void setClassId(Integer classId) {
        ClassId = classId;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getStudentDesc() {
        return studentDesc;
    }

    public void setStudentDesc(String studentDesc) {
        this.studentDesc = studentDesc;
    }

    @Override
    public String toString() {
        return "StudentParent{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", parUid=" + parUid +
                ", ClassId=" + ClassId +
                ", parentPhoneNumber='" + parentPhoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", idType=" + idType +
                ", studentDesc='" + studentDesc + '\'' +
                ", notifyFlag=" + notifyFlag +
                '}';
    }
}
