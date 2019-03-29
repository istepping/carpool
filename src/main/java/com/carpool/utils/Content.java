package com.carpool.utils;

import com.carpool.dto.CarpoolListDto;
import com.carpool.entity.CarpoolList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sunLei on 2019/3/29 14:27
 * @version 1.0
 * @apiNote 缓存
 */
public class Content {
    /**拼单数据*/
    public static Map<String, List<CarpoolListDto>> dataCache=new ConcurrentHashMap<>();
    /**拼单总数创建时间*/
    public static int carpoolListByCreateTimeTotal=0;
    /**拼单总数集合时间*/
    public static int carpoolListByTimeTotal=0;
    /**缓存时间*/
    public static Long cacheTimeCarpoolListByCreateTime=0L;
    public static Long cacheTimeCarpoolListByTime=0L;
    /**有效时间*/
    public final static Long validTime=60*1000L;
}
