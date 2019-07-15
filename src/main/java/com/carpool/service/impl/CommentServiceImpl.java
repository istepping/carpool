package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CommentMapper;
import com.carpool.entity.Comment;
import com.carpool.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunLei on 2019/7/15 14:54
 * @version 1.0
 */
@Service
public class CommentServiceImpl extends BaseService implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public ServiceResult add(Comment comment) {
        commentMapper.insertSelective(comment);
        return success();
    }

    @Override
    public ServiceResult get(Long pId) {
        Map<String, Object> data = new HashMap<>();
        List<Comment> comments = commentMapper.selectList(pId);
        data.put("comments", comments);
        return success(data);
    }
}
