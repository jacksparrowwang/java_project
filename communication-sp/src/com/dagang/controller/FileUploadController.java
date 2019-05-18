package com.dagang.controller;


import com.dagang.model.FileUploadDownloadModel;
import com.dagang.service.FileSystemService;
import com.dagang.service.FileSystemServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/7 14:59
 */

@WebServlet(value = "/upLoadFile")
public class FileUploadController extends HttpServlet {

    private FileSystemService fileSystemService = new FileSystemServiceImpl();

    // 文件名字
    private String fileName;
    // 班级ID
    private Integer classId;
    // 文件保存路径
    private String folderPath;
    // 上传人电话
    private String phone;
    // 上传人名字
    private String uploadUser;
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException {
        phone = (String) req.getSession().getAttribute("user");
        //1.判断是否为文件上传表单
        if(ServletFileUpload.isMultipartContent(req)){
            //2.创建个文件工厂
            FileItemFactory factory = new DiskFileItemFactory();
            //3.创建文件上传对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            //4.解析请求
            try{
                List<FileItem> items = fileUpload.parseRequest(req);
                // 这样写的目的是为了保证fileName和classId都能赋值
                for(FileItem item : items){
                    if(item.isFormField()){//普通表单字段
                        processFormField(item);
                    }
                }
                for(FileItem item : items){
                    if(!item.isFormField()){
                        processFile(item);
                    }
                }
            }catch (FileUploadException fue){
                fue.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 表示成功
            resp.getWriter().write("1");
            // 进行数据库操作
        }else {
            // 表示失败
            resp.getWriter().write("2");
        }
    }
    private void processFile(FileItem item) throws Exception {
//        String fieldName = item.getFieldName();
//        String fieldValue = item.getString();
        //获取文件扩展名
//        String suffix = item.getName().split("\\.")[1];
//        String name = item.getName().split("\\.")[0];

        // 1.获取文件名字
        fileName = item.getName();
        System.out.println(fileName +  ":" + item.getContentType());

        // 进行数据库查询，看是否有相同文件
        int i = 0;
        while (fileSystemService.isEqualsFileName(classId,fileName)) {
            String suffix = fileName.split("\\.")[1];
            fileName = fileName.split("\\.")[0] + "("+ i +")"+suffix;
            ++i;
        }

        //2.创建文件保存的文件夹
        folderPath = this.getServletContext().getRealPath("/WEB-INF/fileTest");
        System.out.println("保存的路径:" + folderPath);
        File file = new File(folderPath);
        if(!file.exists()){
            file.mkdirs();
        }

        //3.保存文件
        //文件完整保存路径
        String filePath = folderPath + "/" + fileName;
        try {
            item.write(new File(filePath));
            item.delete();//删除临时文件

            // 插入文件info到数据库
            if (!insertFileInfo()) {
                throw new Exception("文件信息插入数据库失败");
            }

            // 通知系统的入口
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean insertFileInfo() {
        FileUploadDownloadModel fileUploadDownloadModel = new FileUploadDownloadModel();
        fileUploadDownloadModel.setClassId(classId);
        fileUploadDownloadModel.setUpLoadUserPhone(phone);
        fileUploadDownloadModel.setDate(System.currentTimeMillis());
        fileUploadDownloadModel.setFilePath(folderPath);
        fileUploadDownloadModel.setFileName(fileName);
        fileUploadDownloadModel.setUpLoadUserName(uploadUser);

        if (!fileSystemService.insertFileInfo(fileUploadDownloadModel)) {
            return false;
        }
        return true;
    }

    private void processFormField(FileItem item) {
        String fieldName = item.getFieldName();
        String fieldValue = item.getString();
        if ("classId".equals(fieldName)) {
            System.out.println("普通字段的值"+fieldValue+"   ； "+fieldName);
            classId = Integer.parseInt(fieldValue);
        }
        if ("uploadUser".equals(fieldName)) {
            uploadUser = fieldValue;
        }
        System.out.println(fieldName + ":" + fieldValue);
    }
}