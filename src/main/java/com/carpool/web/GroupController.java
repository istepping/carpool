package com.carpool.web;

import com.carpool.base.BaseController;
import com.carpool.base.BaseService;
import com.carpool.entity.Group;
import com.carpool.service.GroupService;
import com.carpool.service.JoinGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private JoinGroupService joinGroupService;
    //加入群聊
    @RequestMapping("/joinGroup")
    @ResponseBody
    public Result joinGroup(String gId){
        BaseService.ServiceResult result=joinGroupService.joinGroup(Long.valueOf(gId),userMap.get(getRequest().getHeader("authorization")));
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse(result.getInfo());
        }
    }
    //退出群聊
    @RequestMapping("/quitGroup")
    @ResponseBody
    public Result quitGroup(String gId){
        BaseService.ServiceResult result=joinGroupService.quitGroup(Long.valueOf(gId),userMap.get(getRequest().getHeader("authorization")));
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse(result.getInfo());
        }
    }
    //修改群聊信息
    @RequestMapping("/changeGroupInfo")
    @ResponseBody
    public Result changeGroupInfo(Long gId, String gName, String gNotice, String gDescription){
        BaseService.ServiceResult result=groupService.changeGroupInfo(new Group(gId,gName,gNotice,gDescription));
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse(result.getInfo());
        }
    }
}
