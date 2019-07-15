package com.carpool.service.impl;

import com.carpool.base.BaseService;
import com.carpool.dao.PostMapper;
import com.carpool.entity.Post;
import com.carpool.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ServiceResult add(Post post) {
        postMapper.insertSelective(post);
        return success();
    }

    @Override
    public ServiceResult get() {
        Map<String, Object> data = new HashMap<>();
        List<Post> posts = postMapper.selectList();
        data.put("posts", posts);
        return success(data);
    }
}
