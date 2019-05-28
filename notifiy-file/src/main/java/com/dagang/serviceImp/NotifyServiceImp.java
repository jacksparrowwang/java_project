package com.dagang.serviceImp;

import com.dagang.service.NotifyService;

import java.util.Map;

/**
 * @auther wangchenggang
 * @Date 2019/5/24 21:34
 */

public class NotifyServiceImp implements NotifyService {
    @Override
    public boolean notifyWorks(Integer integer) {
        System.out.println("wolaiguo");
        return false;
    }

    @Override
    public Map<String, Integer> queryNotifyInfo(Integer integer) {
        return null;
    }

    @Override
    public boolean acceptNotify(String s) {
        return false;
    }
}
