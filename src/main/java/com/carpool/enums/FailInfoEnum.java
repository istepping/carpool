package com.carpool.enums;

/**
 * @author sunLei on 2018/11/22 16:49
 * @version 1.0
 * @apiNote
 */
public enum FailInfoEnum {
    fail1(-1,"参数不符合要求"),
    fail2(-2,"数据库操作失败");
    private int state;
    private String info;
    FailInfoEnum(int state, String info){
        this.state=state;
        this.info=info;
    }
    public int getState() {
        return state;
    }
    public String getInfo() {
        return info;
    }
    public String getInfoByState(int state){
        for(FailInfoEnum orderStateEnum:values()){
            if(orderStateEnum.getState()==state){
                return orderStateEnum.getInfo();
            }
        }
        return "";
    }
}
