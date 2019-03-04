package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CarpoolListMapper;
import com.carpool.dao.GroupMapper;
import com.carpool.dao.JoinGroupMapper;
import com.carpool.entity.CarpoolList;
import com.carpool.entity.Group;
import com.carpool.entity.JoinGroup;
import com.carpool.service.CarpoolListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.carpool.utils.Assist.print;


/**
 * @author sunLei on 2019/3/2 19:35
 * @version 1.0
 */
@Service
public class CarpoolListServiceImpl extends BaseService implements CarpoolListService {
    @Autowired
    private CarpoolListMapper carpoolListMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private JoinGroupMapper joinGroupMapper;
    @Override
    public void addCarpoolList(CarpoolList carpoolList) {
        carpoolListMapper.insert(carpoolList);
        //创建群聊
        print("lId"+carpoolList.getlId());
        Group group=new Group(carpoolList.getlId(),carpoolList.getlCreateUserId(),carpoolList.getlStartPlace(),0);
        groupMapper.insert(group);
        //默认加入群聊
        joinGroupMapper.insert(new JoinGroup(group.getgId(),carpoolList.getlCreateUserId(),new Date(),1,0));
    }

    @Override
    public ServiceResult changeCarpoolList(CarpoolList carpoolList) {
        Long lId=carpoolList.getlId();
        CarpoolList carpoolList1=carpoolListMapper.selectByPrimaryKey(lId);
        if(carpoolList1==null){
            return fail("查询不到此拼单");
        }else{
            carpoolListMapper.updateByPrimaryKeySelective(carpoolList);
            return success();
        }
    }

    @Override
    public List<CarpoolList> getCarpoolListByCreateTime() {
        return carpoolListMapper.selectByCreateTimeDes();
    }

    @Override
    public List<CarpoolList> getCarpoolListByTime() {
        List<CarpoolList> carpoolLists=carpoolListMapper.selectByCreateTimeDes();
        Date date=new Date();
        int size=carpoolLists.size();
        for(int i=0;i<size-1;i++){
            for(int j=0;j<size-1-i;j++){
                Long time1=carpoolLists.get(j).getlTime().getTime()-date.getTime();
                Long time2=carpoolLists.get(j+1).getlTime().getTime()-date.getTime();
                if(time1>time2){
                    CarpoolList temp=carpoolLists.get(j);
                    carpoolLists.set(j,carpoolLists.get(j+1));
                    carpoolLists.set(j+1,temp);
                }
            }
        }
        return carpoolLists;
    }
}
