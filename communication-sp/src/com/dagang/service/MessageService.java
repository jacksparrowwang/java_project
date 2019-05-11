package com.dagang.service;

import com.dagang.model.ClassMessagePOJO;
import com.dagang.model.GroupMessagesql;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

public interface MessageService {

    public List<GroupMessagesql> queryMessageContentByClassId(Integer classId);

    public GroupMessagesql sendGroupMessage(ClassMessagePOJO classMessagePOJO);

}
