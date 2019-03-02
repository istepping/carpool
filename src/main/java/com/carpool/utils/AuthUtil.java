package com.carpool.utils;

import com.carpool.entity.User;
import com.carpool.service.UserService;
import com.carpool.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunLei on 2019/3/2 19:55
 * @version 1.0
 */
public class AuthUtil {
    public static Map<String, Long> userMap=new HashMap<>();
    public static Long authLogin(String token){
        Long uId=userMap.get(token);
        if(uId==null){
            UserService userService=new UserServiceImpl();
            User user=userService.getUserByWxId(token);
            if(user!=null){
                userMap.put(token,user.getuId());//加入缓存
            }
        }
        return uId;
    }
}
