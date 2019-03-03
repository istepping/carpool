package com.carpool.web;

import com.carpool.base.BaseController;
import com.carpool.entity.Group;
import com.carpool.entity.User;
import com.carpool.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static com.carpool.utils.AuthUtil.userMap;

/**
 * @author sunLei on 2019/3/3 16:06
 * @version 1.0
 * @apiNote
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController{
    @Autowired
    private GroupService groupService;
//    //创建群聊
//    @RequestMapping("/createGroup")
//    @ResponseBody
//    public Result createGroup(String lId){
//        groupService.createGroup(new Group(Long.valueOf(lId),));
//        return successResponse(data);
//    }
}
