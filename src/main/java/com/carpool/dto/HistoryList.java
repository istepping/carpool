package com.carpool.dto;

import com.carpool.entity.CarpoolList;
import com.carpool.entity.User;

/**
 * @author sunLei on 2019/3/31 16:19
 * @version 1.0 用于返回用户查询历史信息
 */
public class HistoryList {
    private User createUser;//创建者
    private CarpoolList carpoolList;//拼单原始信息
    private Long gId;//对应群Id
    private Integer state;////订单状态,0正常进行,-1:已结束,-2:已删除(删除不返回)

    public HistoryList() {
    }

    public HistoryList(User createUser, CarpoolList carpoolList, Long gId, Integer state) {
        this.createUser = createUser;
        this.carpoolList = carpoolList;
        this.gId = gId;
        this.state = state;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public CarpoolList getCarpoolList() {
        return carpoolList;
    }

    public void setCarpoolList(CarpoolList carpoolList) {
        this.carpoolList = carpoolList;
    }

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
