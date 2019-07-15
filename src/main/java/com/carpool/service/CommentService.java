package com.carpool.service;

import com.carpool.base.BaseService;
import com.carpool.entity.Comment;
import org.springframework.stereotype.Service;

/**
 * @author sunLei on 2019/7/15 14:54
 * @version 1.0
 */
@Service
public interface CommentService {
    BaseService.ServiceResult add(Comment comment);
    BaseService.ServiceResult get(Long pId);
}
