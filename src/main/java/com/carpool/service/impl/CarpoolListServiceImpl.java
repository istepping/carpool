package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CarpoolListMapper;
import com.carpool.entity.CarpoolList;
import com.carpool.service.CarpoolListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunLei on 2019/3/2 19:35
 * @version 1.0
 */
@Service
public class CarpoolListServiceImpl extends BaseService implements CarpoolListService {
    @Autowired
    private CarpoolListMapper carpoolListMapper;
    @Override
    public void addCarpoolList(CarpoolList carpoolList) {
        carpoolListMapper.insertSelective(carpoolList);
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
