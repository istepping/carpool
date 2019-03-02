package com.carpool.dao;

import com.carpool.entity.JoinGroup;

public interface JoinGroupMapper {
    int deleteByPrimaryKey(Long jId);

    int insert(JoinGroup record);

    int insertSelective(JoinGroup record);

    JoinGroup selectByPrimaryKey(Long jId);

    int updateByPrimaryKeySelective(JoinGroup record);

    int updateByPrimaryKey(JoinGroup record);
}