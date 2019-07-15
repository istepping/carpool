package com.carpool.entity;

import java.util.Date;

public class Post {
    private Long pId;

    private Long uId;

    private Date pTime;

    private Integer pLike;

    private String pTitle;

    private String pContent;

    public Post() {
    }

    public Post(Long uId, Date pTime, Integer pLike, String pTitle, String pContent) {
        this.uId = uId;
        this.pTime = pTime;
        this.pLike = pLike;
        this.pTitle = pTitle;
        this.pContent = pContent;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Date getpTime() {
        return pTime;
    }

    public void setpTime(Date pTime) {
        this.pTime = pTime;
    }

    public Integer getpLike() {
        return pLike;
    }

    public void setpLike(Integer pLike) {
        this.pLike = pLike;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle == null ? null : pTitle.trim();
    }

    public String getpContent() {
        return pContent;
    }

    public void setpContent(String pContent) {
        this.pContent = pContent == null ? null : pContent.trim();
    }
}