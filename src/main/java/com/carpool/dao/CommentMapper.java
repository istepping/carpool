package com.carpool.dao;

import com.carpool.entity.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> selectList(Long pId);

    int deleteByPrimaryKey(Long cId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long cId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}