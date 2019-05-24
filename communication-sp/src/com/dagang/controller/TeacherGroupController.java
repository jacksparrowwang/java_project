package com.dagang.controller;

import com.dagang.service.StudentPService;
import com.dagang.service.TeaClaRelationService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @auther wangchenggang
 * @Date 2019/5/7 17:57
 */

@Controller
public class TeacherGroupController {

    @Autowired
    private TeaClaRelationService teaClaRelationService;

    @Autowired
    private StudentPService studentPService;

    @RequestMapping("/teacherChat")
    public String teacherShow() {
        return "chatgroupteacher";
    }

    @RequestMapping(value = "/getClassAndName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getClassAndName(HttpServletRequest request) {
        String phoneNumber = (String) request.getSession().getAttribute("user");
        Map<Integer, String> map = teaClaRelationService.queryClassIdAndClassNameByPhone(phoneNumber);
        if (map == null || map.isEmpty()) {
            System.out.println("getClassAndName:该老师所没有所对应的班级");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(map);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "setClassMember", method = RequestMethod.GET)
    public String setClassMember(HttpServletRequest request, HttpServletResponse response) {

        String query = request.getQueryString();
        System.out.println(query);
        try {
            query = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(query);
        if ("0&noclass".equals(query)) {
            // TODO
            return "noselectclass";
        }
        String[] strings = query.split("&");
        Integer classId = Integer.parseInt(strings[0]);
        String className = strings[1];

        request.setAttribute("className", className);
        request.setAttribute("classId", classId);

        return "addMember";
    }


    @RequestMapping(value = "/queryName", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String queryName(HttpServletRequest request) {
        String phone = request.getQueryString();
        System.out.println("test phone" + phone);

        String[] userName = studentPService.getUserNameByPhoneNu(phone);
        ObjectMapper o = new ObjectMapper();
        String result = null;
        try {
            result = o.writeValueAsString(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/addGroupMember", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String addGroupMember(@RequestBody String query, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("addGroupMember:" + query);

        String[] strings = query.split("&");

        String classIdstr = strings[0].split("=")[1];
        String addPhoneN = strings[1].split("=")[1];
        String userName = strings[2].split("=")[1];

        Integer classId = Integer.parseInt(classIdstr);
        userName = URLDecoder.decode(userName,"UTF-8");
        System.out.println(classId + "   " + addPhoneN + "   " + userName);
        // 进行设置
        boolean flag = false;
        if (userName == null || userName.isEmpty()) {
            flag = studentPService.setClassIdByPhone(addPhoneN, classId);
        } else if (addPhoneN == null || addPhoneN.isEmpty()) {
            // 错误
        } else {
            flag = studentPService.setClassIdByPhone(addPhoneN, classId, userName);
            System.out.println(flag);
        }

        if (flag) {
            return "1";
        }
        return "0";
    }
}


