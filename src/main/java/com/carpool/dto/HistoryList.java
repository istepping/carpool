package com.carpool.dto;

import com.carpool.entity.CarpoolList;
import com.carpool.entity.User;

/**
 * @author sunLei on 2019/3/31 16:19
 * @version 1.0 用于返回用户查询历史信息
 */
public class HistoryList {
    private User createUser;//创建者
    private CarpoolList carpoolList;//拼单原始信息
    private Long gId;//对应群Id
}
