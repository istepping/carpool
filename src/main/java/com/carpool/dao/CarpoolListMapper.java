package com.carpool.dao;

import com.carpool.entity.CarpoolList;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CarpoolListMapper {
    List<CarpoolList> selectByCreatedUId(Long uId);

    Long getLIdByUIdAndCreateTime(@Param("uId") Long uId,@Param("createTime") String createTime);

    List<CarpoolList> selectByCreateTimeDes();

    int deleteByPrimaryKey(Long lId);

    int insert(CarpoolList record);

    int insertSelective(CarpoolList record);

    CarpoolList selectByPrimaryKey(Long lId);

    int updateByPrimaryKeySelective(CarpoolList record);

    int updateByPrimaryKey(CarpoolList record);
}