package com.carpool.entity;

import java.util.Date;

public class Message {
    private Long mId;

    private Long gId;

    private Long uId;

    private String mContent;

    private Date mTime;

    private String mType;

    private Integer mState;

    public Message() {
    }

    public Message(Long gId, Long uId, String mContent, Date mTime, String mType, Integer mState) {
        this.gId = gId;
        this.uId = uId;
        this.mContent = mContent;
        this.mTime = mTime;
        this.mType = mType;
        this.mState = mState;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent == null ? null : mContent.trim();
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType == null ? null : mType.trim();
    }

    public Integer getmState() {
        return mState;
    }

    public void setmState(Integer mState) {
        this.mState = mState;
    }
}