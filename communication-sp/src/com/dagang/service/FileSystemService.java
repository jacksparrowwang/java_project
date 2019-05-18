package com.dagang.service;

import com.dagang.model.FileUploadDownloadModel;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/17 14:16
 */

public interface FileSystemService {
    /**
     *
     * @param classId 班级的Id
     * @param fileName 文件名字
     * @return 返回true为有重复名字的文件，false没有重复的
     */
    public boolean isEqualsFileName (Integer classId,String fileName) throws Exception;

    /**
     *
     * @param fileUploadDownloadModel 构造好的数据
     * @return 返回插入成功还是失败
     */
    public boolean insertFileInfo(FileUploadDownloadModel fileUploadDownloadModel);

    /**
     *
     * @param classId 班级Id
     * @return 返回班级文件的信息
     */
    public List<FileUploadDownloadModel> queryFileInfoByClassId(Integer classId);
}
