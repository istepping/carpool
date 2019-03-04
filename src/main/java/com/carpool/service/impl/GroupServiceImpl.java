package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.GroupMapper;
import com.carpool.entity.Group;
import com.carpool.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunLei on 2019/3/2 19:35
 * @version 1.0
 */
@Service
public class GroupServiceImpl extends BaseService implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public void createGroup(Group group) {
        groupMapper.insertSelective(group);
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
