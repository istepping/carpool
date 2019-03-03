package com.carpool.web;

import com.carpool.base.BaseController;
import com.carpool.base.BaseService;
import com.carpool.entity.CarpoolList;
import com.carpool.service.CarpoolListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.carpool.utils.AuthUtil.userMap;

/**
 * @author sunLei on 2019/3/3 14:12
 * @version 1.0
 * @apiNote
 */
@Controller
@RequestMapping("/carpoolList")
public class CarpoolListController extends BaseController {
    @Autowired
    private CarpoolListService carpoolListService;
    //创建拼单
    @RequestMapping("/addCarpoolList")
    @ResponseBody
    public Result addCarpoolList(String startPlace, String endPlace, String time,String maxNumber,String carpoolMode,String hasCar,String genderRequirement,String extra)throws ParseException {
        if(Integer.valueOf(maxNumber)<=1 || Integer.valueOf(maxNumber)>50){
            return failResponse("最大人数超出限制");
        }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CarpoolList carpoolList=new CarpoolList(userMap.get(getRequest().getHeader("authorization")),startPlace,endPlace,format.parse(time),Integer.valueOf(maxNumber),1,carpoolMode,hasCar,genderRequirement,new Date(),extra,0);
        carpoolListService.addCarpoolList(carpoolList);
        return successResponse();
    }
    //修改拼单
    @RequestMapping("/changeCarpoolList")
    @ResponseBody
    public Result changeCarpoolList(String lId,String startPlace, String endPlace, String time,String maxNumber,String carpoolMode,String hasCar,String genderRequirement,String extra)throws ParseException{
        if(Integer.valueOf(maxNumber)<=1 || Integer.valueOf(maxNumber)>50){
            return failResponse("最大人数超出限制");
        }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CarpoolList carpoolList=new CarpoolList(Long.valueOf(lId),userMap.get(getRequest().getHeader("authorization")),startPlace,endPlace,format.parse(time),Integer.valueOf(maxNumber),carpoolMode,hasCar,genderRequirement,extra);
        BaseService.ServiceResult result=carpoolListService.changeCarpoolList(carpoolList);
        if(result.isSuccess()){
            return successResponse();
        }else{
            return failResponse(result.getInfo());
        }
    }
    //查询最新拼单
    @RequestMapping("/getCarpoolListByCreateTime")
    @ResponseBody
    public Result getCarpoolListByCreateTime(){
        List<CarpoolList> carpoolList=carpoolListService.getCarpoolListByCreateTime();
        Map<String,Object> data=new HashMap<>();
        data.put("carpoolList",carpoolList);
        return successResponse(data);
    }
    //查询最近拼单
    @RequestMapping("/getCarpoolListByTime")
    @ResponseBody
    public Result getCarpoolListByTime(){
        List<CarpoolList> carpoolList=carpoolListService.getCarpoolListByTime();
        Map<String,Object> data=new HashMap<>();
        data.put("carpoolList",carpoolList);
        return successResponse(data);
    }
}
