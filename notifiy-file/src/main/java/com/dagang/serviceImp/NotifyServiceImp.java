package com.dagang.serviceImp;

import com.dagang.model.AcceptAndNot;
import com.dagang.model.StudentSearchModel;
import com.dagang.service.NotifyService;
import com.dagang.servicedao.NotifyDaoMapper;
import com.dagang.servicedao.NotifyStudentParentsMapper;
import com.dagang.model.NotifyModel;
import com.dagang.servicemodel.NotifyStudentMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther wangchenggang
 * @Date 2019/5/24 21:34
 */

@Service
public class NotifyServiceImp implements NotifyService {

    @Autowired
    NotifyDaoMapper notifyDaoMapper;

    @Autowired
    NotifyStudentParentsMapper notifyStudentParentsMapper;

    @Override
    public boolean notifyWorks(NotifyModel notifyModel) {

        System.out.println("Info: notifyWorks coming");
        if (notifyModel == null) {
            System.out.println("NotifyServiceImp:notifyWorks:parameter is null");
            return false;
        }

        //  事件入库
        notifyModel.setTime_eventID(System.currentTimeMillis());
        if (!(notifyDaoMapper.insertNotifyEvent(notifyModel) == 1)) {
            return false;
        }

        // 数据库中拿出本班人员
        List<NotifyStudentMode> list = notifyStudentParentsMapper.queryUidMemberByClassId(notifyModel.getClassId());

        String event = null;
        for (NotifyStudentMode n : list) {
            event = n.getNotify_event() + ";" + notifyModel.getTime_eventID();
            if (!(notifyStudentParentsMapper.setNotifyEventByClassId(n.getParUid(), event) == 1)) {
                System.out.println("修改失败: key:"+n.getParUid()+" event:"+event);
            }
        }
        return true;
    }

    @Override
    public List<NotifyModel> queryNotifyEventByClassId(Integer classId) {
        if (classId == null) {
            System.out.println("queryNotifyEventByClassId:parameter is null");
            return null;
        }

        List<NotifyModel> list = notifyDaoMapper.queryInfoByClassId(classId);
        if (list == null || list.isEmpty()) {
            System.out.println("查询班级没有时间通知");
        }
        return list;
    }

    @Override
    public List<AcceptAndNot> minuteAcceptInfo(String eventId, Integer classId) {
        if (eventId == null || classId == null) {
            System.out.println("minuteAcceptInfo:parameter is null");
            return null;
        }
        List<NotifyStudentMode> list = notifyStudentParentsMapper.queryUidMemberByClassId(classId);
        List<AcceptAndNot> acceptAndNots = new ArrayList<>();
        for (NotifyStudentMode l : list) {
            AcceptAndNot a = new AcceptAndNot();
            if (l.getNotify_event().contains(eventId)) {
                // 里面包含字符串说明没有确认
                a.setFlag("0");  // 标记消息是否已经读取
            } else {
                a.setFlag("1");
            }
            a.setName(l.getStudentName());
            acceptAndNots.add(a);
        }
        return acceptAndNots;
    }

    @Override
    public StudentSearchModel studentSearchEventInfo(String phone, Integer classId) {

        if (phone == null || classId == null) {
            System.out.println("studentSearchEventInfo:parameter is null");
            return null;
        }
        StudentSearchModel studentSearchModel = new StudentSearchModel();
        List<NotifyModel> list = queryNotifyEventByClassId(classId);
        if (list == null || list.isEmpty()) {
            System.out.println("studentSearchEventInfo: 本班级没有通知事件");
            return null;
        }
        studentSearchModel.setAllEvent(list);
        String event =  notifyStudentParentsMapper.queryEventOfOwnByPhone(phone);
        if (event.isEmpty()) {
            System.out.println("studentSearchEventInfo:no phoneNumber of student");
            return null;
        }
        studentSearchModel.setOwnerEvent(event);
        return studentSearchModel;
    }

    @Override
    public boolean acceptNotifyOK(String phone, Long eventId) {
        if (phone == null || eventId == null) {
            System.out.println("acceptNotifyOK：parameter is null");
            return false;
        }
        String ev = ";"+eventId;

        System.out.println("ev:"+ev);
        String ownEvent = notifyStudentParentsMapper.queryEventOfOwnByPhone(phone);
        if (ownEvent.contains(ev)) {
            ownEvent = ownEvent.replace(ev,"");
        }
        return notifyStudentParentsMapper.setEventOfOK(ownEvent, phone);

    }
}
