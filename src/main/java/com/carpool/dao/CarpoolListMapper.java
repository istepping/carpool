package com.carpool.dao;

import com.carpool.entity.CarpoolList;

import java.util.List;

public interface CarpoolListMapper {
    List<CarpoolList> selectByCreateTimeDes();

    int deleteByPrimaryKey(Long lId);

    int insert(CarpoolList record);

    int insertSelective(CarpoolList record);

    CarpoolList selectByPrimaryKey(Long lId);

    int updateByPrimaryKeySelective(CarpoolList record);

    int updateByPrimaryKey(CarpoolList record);
}