package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.CommentMapper;
import com.carpool.dao.UserMapper;
import com.carpool.dto.CommentInfo;
import com.carpool.entity.Comment;
import com.carpool.entity.User;
import com.carpool.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserMapper userMapper;
    @Override
    public ServiceResult add(Comment comment) {
        commentMapper.insertSelective(comment);
        return success();
    }

    @Override
    public ServiceResult get(Long pId) {
        Map<String, Object> data = new HashMap<>();
        List<Comment> comments = commentMapper.selectList(pId);
        List<CommentInfo> commentInfos=new ArrayList<>();
        for(Comment comment:comments){
            User user=userMapper.selectByPrimaryKey(comment.getcUserId());
            commentInfos.add(new CommentInfo(comment,user));
        }
        data.put("comments", commentInfos);
        return success(data);
    }
}
