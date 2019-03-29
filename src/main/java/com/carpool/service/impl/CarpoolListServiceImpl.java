package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CarpoolListMapper;
import com.carpool.dao.GroupMapper;
import com.carpool.dao.JoinGroupMapper;
import com.carpool.dao.UserMapper;
import com.carpool.dto.CarpoolListDto;
import com.carpool.entity.CarpoolList;
import com.carpool.entity.Group;
import com.carpool.entity.JoinGroup;
import com.carpool.entity.User;
import com.carpool.service.CarpoolListService;
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
public class CarpoolListServiceImpl extends BaseService implements CarpoolListService {
    @Autowired
    private CarpoolListMapper carpoolListMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private UserMapper userMapper;
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
    public ServiceResult getCarpoolListByCreateTime(Integer page) {
        List<CarpoolListDto> result;
        Map<String,Object> data=new HashMap<>();
        // 缓存成功
        if((new Date().getTime()-Content.cacheTimeCarpoolListByCreateTime)<Content.validTime){
            print("缓存命中");
            result=Content.dataCache.get("carpoolListByCreateTime"+page);
            if(result!=null){
                data.put("carpoolList",result);
                data.put("total",Content.carpoolListByCreateTimeTotal);
                data.put("page",page);
                return success(data);
            }
        }
        // 缓存失效或未命中
        //查询数据库
        List<CarpoolList> carpoolLists=carpoolListMapper.selectByCreateTimeDes();
        // 删除失效数据time过期
        List<CarpoolList> carpoolLists2=new ArrayList<>();
        for(CarpoolList item:carpoolLists){
            if((item.getlTime().getTime()-new Date().getTime())<0){
                carpoolLists2.add(item);
            }
        }
        carpoolLists.removeAll(carpoolLists2);
        // 分页加入缓存
        int total=carpoolLists.size();
        int n=(total/11)+1;//分组的数量每组10个元素
        for(int i=0;i<n;i++){
            List<CarpoolListDto> subList=new ArrayList<>();
            for(int j=i*10;j<(i+1)*10;j++){
                if(j<total){
                    User user=userMapper.selectByPrimaryKey(carpoolLists.get(j).getlCreateUserId());
                    Group group=groupMapper.selectByLId(carpoolLists.get(j).getlId());
                    subList.add(new CarpoolListDto(carpoolLists.get(j),user.getuNickName(),group.getgId()));
                }
            }
            //每页加入缓存
            Content.dataCache.put("carpoolListByCreateTime"+(i+1),subList);
        }
        //总数加入缓存
        Content.carpoolListByCreateTimeTotal=total;
        //记录时间
        Content.cacheTimeCarpoolListByCreateTime=new Date().getTime();
        // 返回数据
        result=Content.dataCache.get("carpoolListByCreateTime"+page);
        print(result);
        if(result==null){
            return fail("页码不正确");
        }else{
            data.put("carpoolList",result);
            data.put("total",Content.carpoolListByCreateTimeTotal);
            data.put("page",page);
            return success(data);
        }
    }

    @Override
    public ServiceResult getCarpoolListByTime(Integer page) {
        List<CarpoolListDto> result;
        Map<String,Object> data=new HashMap<>();
        // 缓存成功
        if((new Date().getTime()-Content.cacheTimeCarpoolListByTime)<Content.validTime){
            print("缓存命中");
            result=Content.dataCache.get("carpoolListByTime"+page);
            if(result!=null){
                data.put("carpoolList",result);
                data.put("total",Content.carpoolListByTimeTotal);
                data.put("page",page);
                return success(data);
            }
        }

        //获取数据列表
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

        // 删除失效数据time过期
        List<CarpoolList> carpoolLists2=new ArrayList<>();
        for(CarpoolList item:carpoolLists){
            if((item.getlTime().getTime()-new Date().getTime())<0){
                carpoolLists2.add(item);
            }
        }
        carpoolLists.removeAll(carpoolLists2);

        // 分页加入缓存
        int total=carpoolLists.size();
        int n=(total/11)+1;//分组的数量每组10个元素
        for(int i=0;i<n;i++){
            List<CarpoolListDto> subList=new ArrayList<>();
            for(int j=i*10;j<(i+1)*10;j++){
                if(j<total){
                    Group group=groupMapper.selectByLId(carpoolLists.get(j).getlId());
                    User user=userMapper.selectByPrimaryKey(carpoolLists.get(j).getlCreateUserId());
                    subList.add(new CarpoolListDto(carpoolLists.get(j),user.getuNickName(),group.getgId()));
                }
            }
            //每页加入缓存
            Content.dataCache.put("carpoolListByTime"+(i+1),subList);
        }
        //总数加入缓存
        Content.carpoolListByTimeTotal=total;
        //记录时间
        Content.cacheTimeCarpoolListByTime=new Date().getTime();
        // 返回数据
        result=Content.dataCache.get("carpoolListByTime"+page);
        if(result==null){
            return fail("页码不正确");
        }else{
            data.put("carpoolList",result);
            data.put("total",Content.carpoolListByTimeTotal);
            data.put("page",page);
            return success(data);
        }
    }
}
