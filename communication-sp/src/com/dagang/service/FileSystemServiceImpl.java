package com.dagang.service;

import com.dagang.dao.FileManagementMapper;
import com.dagang.model.FileUploadDownloadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/17 14:16
 */

@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Autowired
    private FileManagementMapper fileManagementMapper;

    /**
     *
     * @param classId 班级的Id
     * @param fileName 文件名字
     * @return 返回true为有重复名字的文件，false没有重复的
     */
    @Override
    public boolean isEqualsFileName(Integer classId, String fileName) throws Exception {

        if (classId == null || fileName == null) {
            throw new Exception("FileSystemServiceImpl:isEqualsFileName:parameter is null");
        }



        List<FileUploadDownloadModel> list = getMapperBean().isExistFileName(classId, fileName);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param fileUploadDownloadModel 构造好的数据
     * @return 返回插入成功还是失败
     */
    @Override
    public boolean insertFileInfo(FileUploadDownloadModel fileUploadDownloadModel) {
        if (getMapperBean().insertFileInfo(fileUploadDownloadModel) == 1) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param classId 班级Id
     * @return 返回班级文件的信息，null为没有文件
     */
    @Override
    public List<FileUploadDownloadModel> queryFileInfoByClassId(Integer classId) {
        List<FileUploadDownloadModel> list = fileManagementMapper.selectClassFile(classId);
        if (list == null || list.isEmpty()) {
            System.out.println("queryFileInfoByClassId: 该班级没有文件");
            return null;
        }
        return list;
    }

    @Override
    public boolean isEqualsFile(String md5, String fileName) {
        if (md5 == null || fileName == null) {
            return false;
        }
        String result =  getMapperBean().queryFileByfileNameAndmd5(fileName,md5);
        System.out.println("返回的结果"+result);
        if (result == null || result.isEmpty()) {
            return false;
        }
        return true;
    }

    private FileManagementMapper getMapperBean() {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("classpath:resource/applicationContext.xml");
        FileManagementMapper fileMapper = (FileManagementMapper) applicationContext.getBean("fileManagementMapper");
        return fileMapper;
    }


}
