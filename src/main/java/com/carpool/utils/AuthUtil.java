package com.carpool.utils;

import com.carpool.entity.User;
import com.carpool.service.UserService;
import com.carpool.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
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
            //主动注入
            ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
            UserService userService=(UserService) context.getBean("userServiceImpl");
            User user= userService.getUserByWxId(token);
            if(user!=null){
                uId=user.getuId();
                userMap.put(token,uId);//加入缓存
            }
        }
        return uId;
    }
    public static boolean isEmptyOrNull(String val){
        return val==null || val.equals("");
    }
    public static boolean isNumber(String val){
        if(val==null){
            return false;
        }
        return val.matches("^\\d+$");
    }
}
