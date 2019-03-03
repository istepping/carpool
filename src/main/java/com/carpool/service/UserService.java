package com.carpool.service;


import com.carpool.base.BaseService;
import com.carpool.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author sunLei on 2019/3/2 19:31
 * @version 1.0
 */
@Service
public interface UserService{
    User getUserByWxId(String wxId);
    User getUserInfo(Long uId);
    BaseService.ServiceResult addUserInfo(User user);
}
