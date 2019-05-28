package com.dagang.controller;

import com.dagang.model.FileUploadDownloadModel;
import com.dagang.service.FileSystemService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/17 20:24
 */

@Controller
public class FileDownloadController {

    @Autowired
    private FileSystemService fileSystemService;

    // 刷新
    @RequestMapping(value = "/findFileByClassId",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String findFileByClassId(HttpServletRequest request) {
        String query = request.getQueryString();
        System.out.println("获取参数：findFileByClassId："+query);

        if (query == null || query.isEmpty()) {
            System.out.println("参数为null");
            return null;
        }
        Integer classId = Integer.parseInt(query.trim().split("=")[1]);

        List<FileUploadDownloadModel> list = fileSystemService.queryFileInfoByClassId(classId);
        if (list == null || list.isEmpty()) {
            return "noFile";
        }
        String result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
        String query = request.getQueryString();
        // 附带文件下载请求头
        FileInputStream fileInputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            query = URLDecoder.decode(query,"UTF-8");
            String[] strings = query.split("&");
            String filePath = strings[0].split("=")[1];
            String fileName = strings[1].split("=")[1];

            System.out.println(filePath+" "+ fileName);

            String pathName = filePath+ "\\" +fileName;

            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            // 这是以二进制返回
            response.setContentType("application/octet-stream");
            fileInputStream = new FileInputStream(new File(pathName));

            servletOutputStream = response.getOutputStream();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                servletOutputStream.write(bytes,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileInputStream.close();
                servletOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
