package com.carpool.dto;

import com.carpool.entity.CarpoolList;
import com.carpool.entity.Group;
import com.carpool.entity.JoinGroup;
import com.carpool.entity.User;

import java.util.List;

/**
 * @author sunLei on 2019/3/29 20:10
 * @version 1.0
 * @apiNote
 */
public class GroupInfo {
    private Group group;//群信息
    private CarpoolList carpoolList;//拼单信息
    private User createUser;//创建者信息
    private List<User> users;//加入群的用户信息

    public GroupInfo() {
    }

    public GroupInfo(Group group, CarpoolList carpoolList, User createUser, List<User> users) {
        this.group = group;
        this.carpoolList = carpoolList;
        this.createUser = createUser;
        this.users = users;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public CarpoolList getCarpoolList() {
        return carpoolList;
    }

    public void setCarpoolList(CarpoolList carpoolList) {
        this.carpoolList = carpoolList;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
