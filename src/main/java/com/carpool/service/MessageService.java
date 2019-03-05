package com.carpool.service;

import com.carpool.base.BaseService;
import com.carpool.entity.Message;
import org.springframework.stereotype.Service;

/**
 * @author sunLei on 2019/3/2 19:32
 * @version 1.0
 */
@Service
public interface MessageService {
    BaseService.ServiceResult sendMessage(Message message);
    BaseService.ServiceResult getMessage(Long gId,Long uId);
}
