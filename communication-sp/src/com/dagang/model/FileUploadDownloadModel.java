package com.dagang.model;

/**
 * @auther wangchenggang
 * @Date 2019/5/7 15:29
 */

public class FileUploadDownloadModel {

    private Integer id;
    // 上传时间
    private Long date;
    // 文件名字
    private String fileName;
    // 所对应的班级
    private Integer classId;
    // 文件路径
    private String filePath;
    // 上传人
    private String upLoadUserName;
    // 下载次数
    private Integer downloadNumber;
}
