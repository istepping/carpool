package com.carpool.service;

import com.carpool.base.BaseService;
import com.carpool.entity.Post;
import org.springframework.stereotype.Service;

/**
 * @author sunLei on 2019/7/15 14:53
 * @version 1.0
 */
@Service
public interface PostService {
    BaseService.ServiceResult add(Post post);
    BaseService.ServiceResult get();
    BaseService.ServiceResult like(Long pId);
}
