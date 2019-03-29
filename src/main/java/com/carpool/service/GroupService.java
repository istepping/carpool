package com.carpool.service;

import com.carpool.base.BaseService;
import com.carpool.entity.Group;
import org.springframework.stereotype.Service;

/**
 * @author sunLei on 2019/3/2 19:32
 * @version 1.0
 */
@Service
public interface GroupService {
    void createGroup(Group group);
    BaseService.ServiceResult changeGroupInfo(Group group);
    BaseService.ServiceResult getGroup(Long gId);
}
