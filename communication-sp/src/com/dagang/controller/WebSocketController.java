package com.dagang.controller;

import com.dagang.Util.RandomNameUtil;
import com.dagang.model.GroupMessage;
import com.dagang.model.MessageOnline;
import com.dagang.service.StudentPService;
import com.dagang.service.StudentPServiceImpl;
import com.dagang.service.TeacherService;
import com.dagang.service.TeacherServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @auther wangchenggang
 * @Date 2019/4/29 18:04
 */


@ServerEndpoint("/webSocket")
@Controller
public class WebSocketController {


    private TeacherService teacherService = new TeacherServiceImpl();

    private StudentPService studentPService = new StudentPServiceImpl();

    private Session session;
    private String username;

    private static CopyOnWriteArraySet<WebSocketController> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, String> map = new HashMap<>();

    private RandomNameUtil randomNameUtil = new RandomNameUtil();

    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        //获取用户名
        String s = session.getQueryString();
        String urlUsername = s.split("=")[1];
        try {
            String niName = URLDecoder.decode(urlUsername, "UTF-8");
// 给每条消息添加上classId进行判断是那个群里的，再进行筛选
            //把SessionID和用户名放进集合里面
            username = randomNameUtil.selectName();
            while (map.containsValue(niName)){
                // 这里群里的人员不能超过51个人
                username = randomNameUtil.selectName();
            }
            map.put(session.getId(), username);
            System.out.println("有新的连接，总数：" + webSockets.size() + "  sessionId：" + session.getId() + "  " + username);
            String megs = username  + " 上线！";
            GroupMessage message = new GroupMessage();
            message.setMessage(megs);
            message.setOnlineName(map);
            message.setDateF(new Date());
            System.out.println("message+++===+======+=++==========================="+message);
            ObjectMapper objectMapper = new ObjectMapper();
            send(objectMapper.writeValueAsString(message));
            System.out.println(objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        map.remove(session.getId());
        System.out.println("有新的断开，总数：" + webSockets.size() + "  sessionId：" + session.getId());
        String content = "\"" + username + "\"  离开了聊天室！";
        GroupMessage message = new GroupMessage();
        message.setMessage(content);
        message.setOnlineName(map);
        message.setDateF(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            send(objectMapper.writeValueAsString(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @OnMessage
    public void onMessage(String json) {

        System.out.println("=======++++++====="+json);
        ObjectMapper objectMapper = new ObjectMapper();
        MessageOnline messageOnline = null;
        try {
            messageOnline = objectMapper.readValue(json, MessageOnline.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (messageOnline.getType() == 1) {
            //广播
            GroupMessage groupMessage = new GroupMessage();
            groupMessage.setMassage(this.username,messageOnline.getMessage());
            groupMessage.setOnlineName(map);
            try {
                send(objectMapper.writeValueAsString(groupMessage));
                System.out.println(objectMapper.writeValueAsString(groupMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //单聊
            GroupMessage groupMessage = new GroupMessage();
            groupMessage.setMassage(this.username,messageOnline.getMessage());
            groupMessage.setOnlineName(map);

            String to = messageOnline.getTo();
            String tos[] = to.substring(0, to.length() - 1).split("-");
            List<String> lists = Arrays.asList(tos);
            for (WebSocketController webSocket : webSockets) {
                if (lists.contains(webSocket.session.getId()) && webSocket.session.getId() != this.session.getId()) {
                    try {
                        webSocket.session.getBasicRemote().sendText(objectMapper.writeValueAsString(groupMessage));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void send(String message) {
        for (WebSocketController webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
