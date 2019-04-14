package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CarpoolListMapper;
import com.carpool.dao.GroupMapper;
import com.carpool.dao.JoinGroupMapper;
import com.carpool.entity.CarpoolList;
import com.carpool.entity.Group;
import com.carpool.entity.JoinGroup;
import com.carpool.service.JoinGroupService;
import com.carpool.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.carpool.utils.Assist.print;

/**
 * @author sunLei on 2019/3/2 19:34
 * @version 1.0
 */
@Service
public class JoinGroupServiceImpl extends BaseService implements JoinGroupService {
    @Autowired
    private JoinGroupMapper joinGroupMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private CarpoolListMapper carpoolListMapper;

    @Override
    public ServiceResult joinGroup(Long gId, Long uId) {
        if (gId == null || uId == null || groupMapper.selectByPrimaryKey(gId)==null) {
            return failInput();
        }
        //已经在群聊中
        JoinGroup joinGroup=joinGroupMapper.selectByGIdAndUId(gId, uId);
        if (joinGroup != null && joinGroup.getjState()==0) {
            print("已经成功");
            return success("已经加入");
        }
        if(joinGroup==null){
            joinGroupMapper.insert(new JoinGroup(gId, uId, new Date(), 0, 0));
        }else{
            joinGroupMapper.updateByPrimaryKeySelective(new JoinGroup(joinGroup.getjId(),gId, uId, new Date(), 0, 0));
        }
        Group group = groupMapper.selectByPrimaryKey(gId);
        CarpoolList carpoolList = carpoolListMapper.selectByPrimaryKey(group.getlId());
        carpoolList.setlNumber(carpoolList.getlNumber() + 1);
        carpoolListMapper.updateByPrimaryKeySelective(carpoolList);
        return success("加入成功");
    }

    @Override
    public ServiceResult quitGroup(Long gId, Long uId) {
        if (gId == null || uId == null || groupMapper.selectByPrimaryKey(gId)==null) {
            return failInput();
        }
        //已经在群聊中
        JoinGroup joinGroup=joinGroupMapper.selectByGIdAndUId(gId, uId);
        if (joinGroup != null && joinGroup.getjState()==0) {
            joinGroup.setjState(-1);
            joinGroupMapper.updateByPrimaryKeySelective(joinGroup);
            Group group = groupMapper.selectByPrimaryKey(gId);
            CarpoolList carpoolList = carpoolListMapper.selectByPrimaryKey(group.getlId());
            carpoolList.setlNumber(carpoolList.getlNumber() - 1);
            carpoolListMapper.updateByPrimaryKeySelective(carpoolList);
            return success("退出成功");
        }else{
            return success("已经退出");
        }
    }
}
