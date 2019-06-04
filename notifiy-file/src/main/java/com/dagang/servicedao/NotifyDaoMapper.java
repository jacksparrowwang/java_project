package com.dagang.servicedao;

import com.dagang.model.NotifyModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther wangchenggang
 * @Date 2019/4/20 13:19
 */

@Repository
public interface NotifyDaoMapper {

    public int insertNotifyEvent(NotifyModel notifyModel);

    public List<NotifyModel> queryInfoByClassId(Integer classId);

}
