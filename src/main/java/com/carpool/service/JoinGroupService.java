package com.carpool.service;

import com.carpool.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author sunLei on 2019/3/2 19:32
 * @version 1.0
 */
@Service
public interface JoinGroupService {
    BaseService.ServiceResult joinGroup(Long gId,Long uId);
    BaseService.ServiceResult quitGroup(Long gId,Long uId);
}
