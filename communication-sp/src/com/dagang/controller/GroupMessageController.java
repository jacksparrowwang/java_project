package com.dagang.controller;

import com.dagang.model.ClassMessagePOJO;
import com.dagang.model.GroupMessagesql;
import com.dagang.service.MessageService;
import com.dagang.service.TeacherService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @auther wangchenggang
 * @Date 2019/5/7 17:55
 */

@Controller
public class GroupMessageController {

    @Autowired
     private MessageService messageService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/getMessage",method = RequestMethod.GET , produces = "application/json;charset=utf-8")
    public @ResponseBody String getMessageOfClassId(HttpServletRequest request) {
        System.out.println("传入参数为null"+request.getQueryString());
        Integer classId =Integer.parseInt(request.getQueryString());
        if (classId == null) {
            System.out.println("GroupMessageController : getMessageOfClassId : param is null");
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(messageService.queryMessageContentByClassId(classId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("controller result"+result);
        // 可以为null
        return result;
    }

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String sendMessage(@RequestBody String message,HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassMessagePOJO classMessagePOJO = null;
        String result = null;
        try {
            classMessagePOJO = objectMapper.readValue(message, ClassMessagePOJO.class);

            classMessagePOJO.setPhoneNumber((String) request.getSession().getAttribute("user"));
            classMessagePOJO.setIden((Integer) request.getSession().getAttribute("iden"));
            System.out.println(classMessagePOJO.getClassId());
            System.out.println(classMessagePOJO.getMessage());
            GroupMessagesql groupMessagesql = messageService.sendGroupMessage(classMessagePOJO);
            if (groupMessagesql == null) {
                System.out.println("GroupMessageController:sendMessage: sendMessage failed");
                // 发送失败
                return null;
            }
            // 成功获取发送人信息，返回到界面
            result = objectMapper.writeValueAsString(groupMessagesql);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
