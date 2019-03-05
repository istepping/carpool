package com.carpool.web;

import com.carpool.base.BaseController;
import com.carpool.base.BaseService;
import com.carpool.entity.Message;
import com.carpool.service.MessageService;
import com.carpool.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static com.carpool.utils.AuthUtil.userMap;

/**
 * @author sunLei on 2019/3/5 16:03
 * @version 1.0
 * @apiNote
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
    @Autowired
    private MessageService messageService;
    //发送消息
    @RequestMapping("/sendMessage")
    @ResponseBody
    public Result sendMessage(String gId,String content){
        if(!AuthUtil.isNumber(gId) || content==null || content.length()>255){
            return failInputResponse();
        }
        Message message=new Message(Long.valueOf(gId),userMap.get(getRequest().getHeader("authorization")),content,new Date(),"文本",0);
        BaseService.ServiceResult result=messageService.sendMessage(message);
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse(result.getInfo());
        }
    }
    //获取消息
    @RequestMapping("/getMessage")
    @ResponseBody
    public Result getMessage(String gId){
        if(gId==null){
            return failInputResponse();
        }
        BaseService.ServiceResult result=messageService.getMessage(Long.valueOf(gId),userMap.get(getRequest().getHeader("authorization")));
        if(result.isSuccess()){
            return successResponse(result.getData());
        }else{
            return failResponse(result.getInfo());
        }
    }
}
