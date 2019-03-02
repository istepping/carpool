package com.carpool.web;

import com.carpool.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sunLei on 2019/3/2 19:37
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    //查询用户信息
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo(){
        return successResponse();
    }
}
