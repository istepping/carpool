package com.carpool.dao;

import com.carpool.entity.JoinGroup;
import org.apache.ibatis.annotations.Param;

public interface JoinGroupMapper {
    JoinGroup selectByGIdAndUId(@Param("gId")Long gId,@Param("uId")Long uId);

    int deleteByPrimaryKey(Long jId);

    int insert(JoinGroup record);

    int insertSelective(JoinGroup record);

    JoinGroup selectByPrimaryKey(Long jId);

    int updateByPrimaryKeySelective(JoinGroup record);

    int updateByPrimaryKey(JoinGroup record);
}