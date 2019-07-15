package com.carpool.entity;

import java.util.Date;

public class Comment {
    private Long cId;

    private Long pId;

    private Long cUserId;

    private Date cTime;

    private String cContent;

    public Comment() {
    }

    public Comment(Long pId, Long cUserId, Date cTime, String cContent) {
        this.pId = pId;
        this.cUserId = cUserId;
        this.cTime = cTime;
        this.cContent = cContent;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Long getcUserId() {
        return cUserId;
    }

    public void setcUserId(Long cUserId) {
        this.cUserId = cUserId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent == null ? null : cContent.trim();
    }
}