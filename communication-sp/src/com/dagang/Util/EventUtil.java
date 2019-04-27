package com.dagang.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 15:48
 */

public class EventUtil {
    public static int productUid() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public static boolean isLoginAndTeacher(HttpServletRequest request) {
        String user = (String) request.getSession().getAttribute("user");
        String pass = (String) request.getSession().getAttribute("pass");
        String iden = (String) request.getSession().getAttribute("iden");

        if (user == null || user.isEmpty() || pass == null || pass.isEmpty() || iden == null || iden.isEmpty()) {
            System.out.println("没有session对象内容");
            return false;
        }
        System.out.println("检查是否是老师并且登陆");
        // 来判断是否是登陆状态
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            boolean flag = true;
            for (Cookie c : cookies) {
                if ("user".equals(c.getName())) {
                    if (!user.equals(c.getValue())) {
                        flag = false;
                    }
                }
                if ("pass".equals(c.getName())) {
                    if (!pass.equals(c.getValue())) {
                        flag = false;
                    }
                }
                if ("iden".equals(c.getName())) {
                    if (!iden.equals(c.getValue())){
                        flag = false;
                    }
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static void setCookieAndSession(HttpServletRequest request, HttpServletResponse response,
                                     String phone, String pass, String iden) {
        Cookie userCookie = new Cookie("user", phone);
        Cookie passCookie = new Cookie("pass", pass);
        Cookie idenCookie = new Cookie("iden", iden);
        response.addCookie(userCookie);
        response.addCookie(passCookie);
        response.addCookie(idenCookie);
        request.getSession().setAttribute("user", phone);
        request.getSession().setAttribute("pass", pass);
        request.getSession().setAttribute("iden", iden);
        request.getSession().setMaxInactiveInterval(3600*12);
    }
}
