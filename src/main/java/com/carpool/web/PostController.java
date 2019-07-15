package com.carpool.web;

import com.carpool.base.BaseController;
import com.carpool.base.BaseService;
import com.carpool.entity.Post;
import com.carpool.service.PostService;
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
@RequestMapping("/post")
public class PostController extends BaseController{
    @Autowired
    private PostService postService;
    @RequestMapping("/add")
    @ResponseBody
    public Result add(String title,String content){
        BaseService.ServiceResult result=postService.add(new Post(userMap.get(getRequest().getHeader("authorization")),new Date(),0,title,content));
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse(result.getInfo());
        }
    }
    @RequestMapping("/get")
    @ResponseBody
    public Result get(){
        BaseService.ServiceResult result=postService.get();
        if(result.isSuccess()){
            System.out.print(result.getData());
            return successResponse(result.getData());
        }else{
            return failResponse(result.getInfo());
        }
    }
}
