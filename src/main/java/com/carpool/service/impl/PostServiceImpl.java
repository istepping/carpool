package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.PostMapper;
import com.carpool.dao.UserMapper;
import com.carpool.dto.PostInfo;
import com.carpool.entity.Post;
import com.carpool.entity.User;
import com.carpool.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunLei on 2019/7/15 14:53
 * @version 1.0
 */
@Service
public class PostServiceImpl extends BaseService implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServiceResult add(Post post) {
        postMapper.insertSelective(post);
        return success();
    }

    @Override
    public ServiceResult get() {
        Map<String, Object> data = new HashMap<>();
        List<Post> posts = postMapper.selectList();
        List<PostInfo> postInfos=new ArrayList<>();
        for(Post post:posts){
            User user=userMapper.selectByPrimaryKey(post.getuId());
            postInfos.add(new PostInfo(post,user));
        }
        data.put("posts", postInfos);
        return success(data);
    }

    @Override
    public ServiceResult like(Long pId) {
        Post post=postMapper.selectByPrimaryKey(pId);
        post.setpLike(post.getpLike()+1);
        postMapper.updateByPrimaryKeySelective(post);
        return success();
    }
}
