package com.dagang.controller;

import com.dagang.model.AcceptAndNot;
import com.dagang.model.StudentSearchModel;
import com.dagang.service.NotifyService;
import com.dagang.model.NotifyModel;
import com.dagang.service.StudentPService;
import com.dagang.util.Jackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/26 20:41
 */

@Controller
public class NotifyController {

    @Autowired
    NotifyService notifyService;

    @Autowired
    StudentPService studentPService;

    @RequestMapping(value = "/notifyEvent", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String notifyEvent(@RequestBody String string) throws IOException {
        if (string == null || string.isEmpty()) {
            System.out.println("notifyEvent：parameter is null");
            return null;
        }
        NotifyModel notifyModel = Jackson.getObjectMapper().readValue(string,NotifyModel.class);
        System.out.println("notifyEvent:参数显示："+ string);

        boolean result = notifyService.notifyWorks(notifyModel);
        System.out.println(result);
        if (result) {
            return "1";
        }
        return "0";
    }

    @RequestMapping(value = "/searchNotify", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String searchNotify(HttpServletRequest request) throws UnsupportedEncodingException {
        String query = request.getQueryString();
        query = URLDecoder.decode(query,"UTF-8");
        System.out.println("query:"+query);

        String[] strings = query.split("&");

        request.setAttribute("classId",strings[0]);
        request.setAttribute("className", strings[1]);
        return "notifyAcceptMember";
    }

    @RequestMapping(value = "/searchNotifyEvent", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String searchNotifyEvent(HttpServletRequest request) throws IOException {
        String query = request.getQueryString();
        System.out.println("query ::: "+query);

        Integer classId = Integer.parseInt(query.split("=")[1]);
        List<NotifyModel> list = notifyService.queryNotifyEventByClassId(classId);
        if (list == null || list.isEmpty()) {
            // 标识本班级没有通知的事件
            return "0";
        }
        return Jackson.getObjectMapper().writeValueAsString(list);
    }

    @RequestMapping(value = "/minuteInfo",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String minuteInfo(HttpServletRequest request) throws IOException {
        String query = request.getQueryString();
        System.out.println("minuteInfo:query:"+query);

        String[] strings = query.split("&");
        String eventId = strings[0].split("=")[1];
        Integer classId = Integer.parseInt(strings[1].split("=")[1]);

        List<AcceptAndNot> list = notifyService.minuteAcceptInfo(eventId,classId);
        if (list == null || list.isEmpty()) {
            // 标识该班级中没有学生
            return "0";
        }
        return Jackson.getObjectMapper().writeValueAsString(list);
    }

    // ==========学生端=========

    @RequestMapping(value = "/notifyStudentParents",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String notifyStudentParents(HttpServletRequest request) throws UnsupportedEncodingException {
        String query = request.getQueryString();
        query = URLDecoder.decode(query,"UTF-8");
        System.out.println("LookLook:"+query);

        request.setAttribute("className",query.split("&")[0]);
        request.setAttribute("classId",query.split("&")[1]);

        return "notifyStudentAccept";
    }

    @RequestMapping(value = "/minuteStudentEventInfo",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String minuteStudentEventInfo(HttpServletRequest request) throws IOException {
        String query = request.getQueryString();
        System.out.println("minuteStudentEventInfo: "+query);
        String phone = (String) request.getSession().getAttribute("user");
        Integer classId = Integer.parseInt(query.split("=")[1]);

        StudentSearchModel studentSearchModel = notifyService.studentSearchEventInfo(phone,classId);
        if (studentSearchModel == null) {
            return "0";
        }

        return Jackson.getObjectMapper().writeValueAsString(studentSearchModel);
    }

    @RequestMapping("/isOk")
    public @ResponseBody String isOk(HttpServletRequest request) {
        String query = request.getQueryString();
        System.out.println("isOk:"+query);
        Long eventId = Long.parseLong(query.split("=")[1]);
        String phone = (String) request.getSession().getAttribute("user");
        System.out.println("phone:"+phone);
        if(notifyService.acceptNotifyOK(phone,eventId)) {
            return "1"; //成功
        }
        return "0"; // 失败
    }

    @RequestMapping("/isExistNotify")
    public @ResponseBody String isExistNotify(HttpServletRequest request) {
        String phone = (String) request.getSession().getAttribute("user");

        if (studentPService.isExistNewNotify(phone)) {
            return "0"; // 没有新的通知
        }
        return "1"; //  有通知
    }
}
