package com.dagang.model;

import java.io.Serializable;

/**
 * @auther wangchenggang
 * @Date 2019/5/31 18:40
 */

public class AcceptAndNot implements Serializable {

    // 是否确认，1确认，0未确认
    private String flag;

    // 学生的名字
    private String name;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AcceptAndNot{" +
                "flag='" + flag + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
