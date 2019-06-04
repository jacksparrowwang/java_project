package com.dagang.util;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @auther wangchenggang
 * @Date 2019/5/30 15:38
 */

public class Jackson {

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
