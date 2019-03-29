package com.carpool.dto;

import com.carpool.entity.CarpoolList;

/**
 * @author sunLei on 2019/3/29 17:51
 * @version 1.0
 */
public class CarpoolListDto {
    private CarpoolList carpoolList;
    private String createName;
    public CarpoolListDto() {
    }

    public CarpoolListDto(CarpoolList carpoolList, String createName) {
        this.carpoolList = carpoolList;
        this.createName = createName;
    }

    public CarpoolList getCarpoolList() {
        return carpoolList;
    }

    public void setCarpoolList(CarpoolList carpoolList) {
        this.carpoolList = carpoolList;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
