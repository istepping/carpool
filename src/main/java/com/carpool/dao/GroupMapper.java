package com.carpool.dao;

import com.carpool.entity.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(Long gId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long gId);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
}