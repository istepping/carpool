package com.carpool.web;

import com.carpool.base.BaseController;
import com.carpool.base.BaseService;
import com.carpool.entity.User;
import com.carpool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static com.carpool.utils.AuthUtil.userMap;

/**
 * @author sunLei on 2019/3/2 19:37
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    //用户登陆
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String code){
        System.out.println("登陆请求");
        BaseService.ServiceResult result=userService.login(code);
        if(result.isSuccess()){
            return successResponse(result.getData());
        }else{
            return failResponse(result.getInfo());
        }
    }
    //查询用户信息
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo(){
        User user=userService.getUserInfo(userMap.get(getRequest().getHeader("authorization")));
        Map<String,Object> data=new HashMap<>();
        data.put("user",user);
        return successResponse(data);
    }
    //录入用户信息
    @RequestMapping("/addUserInfo")
    @ResponseBody
    public Result addUserInfo(String nickName,String avatarUrl,String gender,String uCity,String province,String language){
        System.out.print("addUserInfo");
        User user=new User(userMap.get(getRequest().getHeader("authorization")),nickName,avatarUrl,gender,uCity,province,language,"0");
        BaseService.ServiceResult result=userService.addUserInfo(user);
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse();
        }
    }
}
