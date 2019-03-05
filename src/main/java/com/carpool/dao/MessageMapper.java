package com.carpool.dao;

import com.carpool.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    List<Message> selectListByGIdAndUId(@Param("gId")Long gId,@Param("uId") Long uId);

    int deleteByPrimaryKey(Long mId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long mId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}