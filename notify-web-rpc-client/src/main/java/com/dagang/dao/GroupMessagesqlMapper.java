package com.dagang.dao;

import com.dagang.model.GroupMessagesql;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/5/8 20:57
 */

@Repository
public interface GroupMessagesqlMapper {

    public List<GroupMessagesql> queryMessageContentByClassId(@Param("classId") Integer classId);

    public boolean insertGroupMessage(GroupMessagesql groupMessagesql);
}
