package com.dagang.model;

import java.io.Serializable;
import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/31 21:41
 */

public class StudentSearchModel implements Serializable {

    // 自己的事件
    private String ownerEvent;

    // 拿到所有的事件
    private List<NotifyModel> allEvent;

    public String getOwnerEvent() {
        return ownerEvent;
    }

    public void setOwnerEvent(String ownerEvent) {
        this.ownerEvent = ownerEvent;
    }

    public List<NotifyModel> getAllEvent() {
        return allEvent;
    }

    public void setAllEvent(List<NotifyModel> allEvent) {
        this.allEvent = allEvent;
    }

    @Override
    public String toString() {
        return "StudentSearchModel{" +
                "ownerEvent='" + ownerEvent + '\'' +
                ", allEvent=" + allEvent +
                '}';
    }
}
