package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.UserMapper;
import com.carpool.entity.User;
import com.carpool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
