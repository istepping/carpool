package com.carpool.service;

import com.carpool.base.BaseService;
import com.carpool.entity.CarpoolList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunLei on 2019/3/2 19:32
 * @version 1.0
 */
@Service
public interface CarpoolListService {
    void addCarpoolList(CarpoolList carpoolList);
    BaseService.ServiceResult changeCarpoolList(CarpoolList carpoolList);
    BaseService.ServiceResult getCarpoolListByCreateTime(Integer page);
    BaseService.ServiceResult getCarpoolListByTime(Integer page);
}
