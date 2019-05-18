package com.dagang.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @auther wangchenggang
 * @Date 2019/5/15 14:26
 */

public class RandomNameUtil {

    private static final String[] strings = {
            "刘备", "关羽", "张飞", "诸葛亮", "孙尚香", "赵云", "关平", "曹操", "张苞", "庞统",
            "马超", "刘禅", "魏延", "严颜", "姜维", "月英", "黄忠", "夏侯渊", "曹仁", "曹丕",
            "张辽", "典韦", "许褚", "夏侯敦", "甄姬", "司马懿", "吕布", "张郃", "满宠", "孙策",
            "孙权", "黄盖", "孙尚香", "周瑜", "太史慈", "周泰", "吕蒙", "陆逊", "凌统", "甘宁",
            "孔融", "小乔", "大乔", "貂蝉", "袁谭", "袁熙", "袁尚", "张宝", "张角", "孟获",
            "祝融"
    };

    public String selectName() {

        Random random = new Random();
        int ran = random.nextInt(50);

        return strings[ran];
    }
}
