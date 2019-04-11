package com.carpool.utils;

import com.carpool.dto.HistoryList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @author 孙磊 on 2018/7/13
 * @version 1.0
 * @apiNote 辅助通用类
 */
public class Assist {
    /**日志输出工具*/
    public static Logger logger=LoggerFactory.getLogger("logger");
    /**
     * @param  o 输出对象
     */
    public static void print(Object o){
        System.out.println(o);
    }
    /**时间排序*/
    public static void sortByTime( List<HistoryList> historyLists){
        //排序
        Date date=new Date();
        int size=historyLists.size();
        for(int i=0;i<size-1;i++){
            for(int j=0;j<size-1-i;j++){
                Long time1=historyLists.get(j).getCarpoolList().getlTime().getTime()-date.getTime();
                Long time2=historyLists.get(j+1).getCarpoolList().getlTime().getTime()-date.getTime();
                if(time1<time2){
                    HistoryList temp=historyLists.get(j);
                    historyLists.set(j,historyLists.get(j+1));
                    historyLists.set(j+1,temp);
                }
            }
        }
    }
}
