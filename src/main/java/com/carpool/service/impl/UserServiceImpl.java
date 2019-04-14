package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CarpoolListMapper;
import com.carpool.dao.GroupMapper;
import com.carpool.dao.JoinGroupMapper;
import com.carpool.dao.UserMapper;
import com.carpool.dto.HistoryList;
import com.carpool.entity.CarpoolList;
import com.carpool.entity.Group;
import com.carpool.entity.JoinGroup;
import com.carpool.entity.User;
import com.carpool.service.UserService;
import com.carpool.utils.API;
import com.carpool.utils.Assist;
import com.carpool.utils.AuthUtil;
import com.carpool.utils.Content;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.carpool.utils.Assist.print;


/**
 * @author sunLei on 2019/3/2 19:33
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CarpoolListMapper carpoolListMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private JoinGroupMapper joinGroupMapper;

    @Override
    public ServiceResult getCreatedList(Long uId) {
        Map<String,Object> data=new HashMap<>();
        // 缓存成功
        if((new Date().getTime()- Content.cacheTimeCreatedListCache)<Content.validTime){
            print("缓存命中");
            data.put("historyList",Content.createdListCache.get(uId));
            return success(data);
        }
        //重新计算
        List<CarpoolList> carpoolLists=carpoolListMapper.selectByCreatedUId(uId);
        List<HistoryList> historyLists=new ArrayList<>();
        for(CarpoolList item:carpoolLists){
            //过滤删除掉的拼单
            if(item.getlState()!=0){
                continue;
            }
            User user=userMapper.selectByPrimaryKey(item.getlCreateUserId());
            int state=0;
            if(item.getlState()==0 && (item.getlTime().getTime()-new Date().getTime()<0)){
                state=-1;
            }
            HistoryList historyList=new HistoryList(user,item,groupMapper.selectByLId(item.getlId()).getgId(),state);
            historyLists.add(historyList);
        }
        Assist.sortByTime(historyLists);
        Content.createdListCache.put(uId,historyLists);
        Content.cacheTimeCreatedListCache=new Date().getTime();
        data.put("historyList",historyLists);
        return success(data);
    }

    @Override
    public ServiceResult getHistoryList(Long uId) {
        Map<String,Object> data=new HashMap<>();
        // 缓存成功
        if((new Date().getTime()- Content.cacheTimeJoinedListCache)<Content.validTime){
            print("缓存命中");
            data.put("historyList",Content.joinedListCache.get(uId));
            return success(data);
        }
        //重新计算

        List<JoinGroup> joinGroups=joinGroupMapper.selectByUId(uId);
        List<HistoryList> historyLists=new ArrayList<>();
        for(JoinGroup item:joinGroups){
            User user=userMapper.selectByPrimaryKey(item.getuId());
            int state=0;
            CarpoolList carpoolList=carpoolListMapper.selectByPrimaryKey(item.getgId());
            if(carpoolList==null){
                continue;
            }
            if(item.getjState()==0 && (carpoolList.getlTime().getTime()-new Date().getTime()<0)){
                state=-1;
            }
            HistoryList historyList=new HistoryList(user,carpoolList,item.getgId(),state);
            historyLists.add(historyList);
        }
        Assist.sortByTime(historyLists);
        Content.joinedListCache.put(uId,historyLists);
        Content.cacheTimeJoinedListCache=new Date().getTime();
        data.put("historyList",historyLists);
        return success(data);
    }

    @Override
    public ServiceResult getJoinList(Long uId) {
        Map<String,Object> data=new HashMap<>();
        // 缓存成功
        if((new Date().getTime()- Content.cacheTimeHistoryListCache)<Content.validTime){
            print("缓存命中");
            data.put("historyList",Content.historyListCache.get(uId));
            return success(data);
        }
        //重新计算

        List<JoinGroup> joinGroups=joinGroupMapper.selectByUId(uId);
        List<HistoryList> historyLists=new ArrayList<>();
        for(JoinGroup item:joinGroups){
            print("jId="+item.getjId());
            //过滤退出的拼单
            if(item.getjState()!=0){
                print("已经退出"+item.getjId());
                continue;
            }
            User user=userMapper.selectByPrimaryKey(item.getuId());
            Group group=groupMapper.selectByPrimaryKey(item.getgId());
            if(group==null){
                continue;
            }
            CarpoolList carpoolList=carpoolListMapper.selectByPrimaryKey(group.getlId());
            //过滤删除的拼单,空拼单,创建的拼单
            if(carpoolList==null || carpoolList.getlCreateUserId().equals(uId) || carpoolList.getlState()!=0){
                print("拼单过滤"+item.getjId());
                continue;
            }
            int state=0;
            if(item.getjState()==0 && (carpoolList.getlTime().getTime()-new Date().getTime()<0)){
                state=-1;
            }
            HistoryList historyList=new HistoryList(user,carpoolList,item.getgId(),state);
            historyLists.add(historyList);
        }
        Assist.sortByTime(historyLists);
        Content.historyListCache.put(uId,historyLists);
        Content.cacheTimeHistoryListCache=new Date().getTime();
        data.put("historyList",historyLists);
        return success(data);
    }

    @Override
    public ServiceResult login(String code) {
        if(AuthUtil.isEmptyOrNull(code)){
            return failInput();
        }
        //获取opentid=wxid
        String grant_type="authorization_code";
        String url="https://api.weixin.qq.com/sns/jscode2session?" + "appid=" + "wx539fa405b462a369" + "&secret=" + "3b61707ae22e4991c630f9629cc8d12e"
                + "&js_code=" + code + "&grant_type=" + grant_type;
        JSONObject response= JSONObject.fromObject(API.INSTANCE.request(url));
        String openid=(String)response.get("openid");
        //查询是否已经注册
        User user=userMapper.selectByWxId(openid);
        if(user==null){
            userMapper.insertSelective(new User(openid));
            user=userMapper.selectByWxId(openid);
        }
        //返回结果
        Map<String,Object> data=new HashMap<>();
        data.put("token",openid);
        data.put("uId",user.getuId());
        data.put("avatar",user.getuAvatarUrl());
        data.put("nickName",user.getuNickName());
        return success(data);
    }

    @Override
    public User getUserByWxId(String wxId) {
        return userMapper.selectByWxId(wxId);
    }

    @Override
    public ServiceResult addUserInfo(User user) {
        if(user.getuId()==null){
            return fail();
        }
        if(userMapper.selectByPrimaryKey(user.getuId())==null){
            userMapper.insertSelective(user);
        }else{
            userMapper.updateByPrimaryKeySelective(user);
        }
        return success();
    }

    @Override
    public User getUserInfo(Long uId) {
        return userMapper.selectByPrimaryKey(uId);
    }
}
