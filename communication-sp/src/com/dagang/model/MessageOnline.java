package com.dagang.model;

import java.util.Arrays;

/**
 * @auther wangchenggang
 * @Date 2019/5/3 11:56
 */

public class MessageOnline {
    private String to;

    private String message;

    private Integer type;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageOnline{" +
                "to='" + to + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}
