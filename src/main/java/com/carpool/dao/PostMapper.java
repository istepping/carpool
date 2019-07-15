package com.carpool.dao;

import com.carpool.entity.Post;

import java.util.List;

public interface PostMapper {
    List<Post> selectList();

    int deleteByPrimaryKey(Long pId);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long pId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKeyWithBLOBs(Post record);

    int updateByPrimaryKey(Post record);
}