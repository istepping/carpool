package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.GroupMapper;
import com.carpool.dao.JoinGroupMapper;
import com.carpool.dao.MessageMapper;
import com.carpool.entity.Group;
import com.carpool.entity.JoinGroup;
import com.carpool.entity.Message;
import com.carpool.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunLei on 2019/3/2 19:34
 * @version 1.0
 */
@Service
public class MessageServiceImpl extends BaseService implements MessageService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private JoinGroupMapper joinGroupMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public ServiceResult sendMessage(Message message) {
        //验证-群信息
        Group group=groupMapper.selectByPrimaryKey(message.getgId());
        if(group==null){
            return fail("群不存在");
        }
        //验证-加群信息
        JoinGroup joinGroup=joinGroupMapper.selectByGIdAndUId(message.getgId(),message.getuId());
        if(joinGroup==null) {
            return fail("该用户不在群中");
        }
        messageMapper.insertSelective(message);
        return success();
    }

    public ServiceResult getMessage(Long gId,Long uId) {
        //验证-群信息
        Group group=groupMapper.selectByPrimaryKey(gId);
        if(group==null){
            return fail("群不存在");
        }
        //验证-加群信息
        JoinGroup joinGroup=joinGroupMapper.selectByGIdAndUId(gId,uId);
        if(joinGroup==null) {
            return fail("该用户不在群中");
        }
        Map<String,Object> data=new HashMap<>();
        List<Message> messages=messageMapper.selectListByGIdAndUId(gId,uId);
        data.put("messages",messages);
        return success(data);
    }
}
