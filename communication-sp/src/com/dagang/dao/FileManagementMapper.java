package com.dagang.dao;

import com.dagang.model.FileUploadDownloadModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

@Repository
public interface FileManagementMapper {

    public List<FileUploadDownloadModel> isExistFileName(@Param("classId") Integer classId,
                                                         @Param("fileName") String fileName);

    public int insertFileInfo(FileUploadDownloadModel fileUploadDownloadModel);

    public List<FileUploadDownloadModel> selectClassFile(Integer classId);
}
