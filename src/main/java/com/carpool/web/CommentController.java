package com.carpool.web;

import com.carpool.base.BaseController;
import com.carpool.base.BaseService;
import com.carpool.entity.Comment;
import com.carpool.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static com.carpool.utils.AuthUtil.userMap;

/**
 * @author sunLei on 2019/7/15 14:51
 * @version 1.0
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/add")
    @ResponseBody
    public Result add(String pId,String content){
        BaseService.ServiceResult result=commentService.add(new Comment(Long.valueOf(pId),userMap.get(getRequest().getHeader("authorization")),new Date(),content));
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse(result.getInfo());
        }
    }
    @RequestMapping("/get")
    @ResponseBody
    public Result get(String pId){
        BaseService.ServiceResult result=commentService.get(Long.valueOf(pId));
        if(result.isSuccess()){
            return successResponse(result.getData());
        }else{
            return failResponse(result.getInfo());
        }
    }
}
