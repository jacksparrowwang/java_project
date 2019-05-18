package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/5/7 15:29
 */

public class FileUploadDownloadModel {

    private Long id;
    // 上传时间
    private Long date;
    // 文件名字
    private String fileName;
    // 所对应的班级
    private Integer classId;
    // 班级名字
    private String className;
    // 文件路径
    private String filePath;
    // 上传人
    private String upLoadUserName;
    // 上传人电话
    private String upLoadUserPhone;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUpLoadUserName() {
        return upLoadUserName;
    }

    public void setUpLoadUserName(String upLoadUserName) {
        this.upLoadUserName = upLoadUserName;
    }

    public String getUpLoadUserPhone() {
        return upLoadUserPhone;
    }

    public void setUpLoadUserPhone(String upLoadUserPhone) {
        this.upLoadUserPhone = upLoadUserPhone;
    }

    @Override
    public String toString() {
        return "FileUploadDownloadModel{" +
                "id=" + id +
                ", date=" + date +
                ", fileName='" + fileName + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", filePath='" + filePath + '\'' +
                ", upLoadUserName='" + upLoadUserName + '\'' +
                ", upLoadUserPhone='" + upLoadUserPhone + '\'' +
                '}';
    }
}
