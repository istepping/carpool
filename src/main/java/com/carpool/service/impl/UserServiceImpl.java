package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.UserMapper;
import com.carpool.entity.User;
import com.carpool.service.UserService;
import com.carpool.utils.API;
import com.carpool.utils.AuthUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.carpool.utils.Assist.print;


/**
 * @author sunLei on 2019/3/2 19:33
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UserMapper userMapper;

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
