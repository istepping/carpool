package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CarpoolListMapper;
import com.carpool.dao.GroupMapper;
import com.carpool.dao.JoinGroupMapper;
import com.carpool.dao.UserMapper;
import com.carpool.dto.GroupInfo;
import com.carpool.entity.CarpoolList;
import com.carpool.entity.Group;
import com.carpool.entity.JoinGroup;
import com.carpool.entity.User;
import com.carpool.service.GroupService;
import com.carpool.utils.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.carpool.utils.Assist.print;

/**
 * @author sunLei on 2019/3/2 19:35
 * @version 1.0
 */
@Service
public class GroupServiceImpl extends BaseService implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private CarpoolListMapper carpoolListMapper;
    @Autowired
    private JoinGroupMapper joinGroupMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void createGroup(Group group) {
        groupMapper.insertSelective(group);
    }

    @Override
    public ServiceResult getGroup(Long gId) {
        GroupInfo groupInfo;
        Map<String,Object> data=new HashMap<>();
        if(new Date().getTime()-Content.groupInfoCacheTime<Content.validTime){
            groupInfo=Content.groupInfoCache.get("groupInfo"+gId);
            if(groupInfo!=null){
                print("缓存命中");
                data.put("groupInfo",groupInfo);
                return success(data);
            }
        }
        //群信息
        Group group=groupMapper.selectByPrimaryKey(gId);
        if(group==null){
            return failInput();
        }
        //拼单信息
        CarpoolList carpoolList=carpoolListMapper.selectByPrimaryKey(group.getlId());
        //群用户信息
        List<JoinGroup> joinGroups=joinGroupMapper.selectByGId(gId);
        List<User> users=new ArrayList<>();
        for(JoinGroup joinGroup: joinGroups){
            users.add(userMapper.selectByPrimaryKey(joinGroup.getuId()));
        }
        groupInfo=new GroupInfo(group,carpoolList,userMapper.selectByPrimaryKey(group.getgCreateUserId()),users);
        Content.groupInfoCache.put("groupInfo"+gId,groupInfo);
        Content.groupInfoCacheTime=new Date().getTime();
        data.put("groupInfo",groupInfo);
        return success(data);
    }

    @Override
    public ServiceResult changeGroupInfo(Group group) {
        if(group.getgId()==null || groupMapper.selectByPrimaryKey(group.getgId())==null){
            return failInput();
        } else{
            groupMapper.updateByPrimaryKeySelective(group);
            return success();
        }
    }
}
