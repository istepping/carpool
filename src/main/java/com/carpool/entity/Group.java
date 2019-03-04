package com.carpool.entity;

public class Group {
    private Long gId;

    private Long lId;

    private Long gCreateUserId;

    private String gName;

    private String gNotice;

    private String gDescription;

    private Integer gState;

    public Group() {
    }

    public Group(Long lId, Long gCreateUserId, String gName, Integer gState) {
        this.lId = lId;
        this.gCreateUserId = gCreateUserId;
        this.gName = gName;
        this.gState = gState;
    }

    public Group(Long gId, String gName, String gNotice, String gDescription) {
        this.gId = gId;
        this.gName = gName;
        this.gNotice = gNotice;
        this.gDescription = gDescription;
    }

    public Group(Long gId, Long lId, Long gCreateUserId, String gName, String gNotice, String gDescription, Integer gState) {
        this.gId = gId;
        this.lId = lId;
        this.gCreateUserId = gCreateUserId;
        this.gName = gName;
        this.gNotice = gNotice;
        this.gDescription = gDescription;
        this.gState = gState;
    }

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public Long getgCreateUserId() {
        return gCreateUserId;
    }

    public void setgCreateUserId(Long gCreateUserId) {
        this.gCreateUserId = gCreateUserId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public String getgNotice() {
        return gNotice;
    }

    public void setgNotice(String gNotice) {
        this.gNotice = gNotice == null ? null : gNotice.trim();
    }

    public String getgDescription() {
        return gDescription;
    }

    public void setgDescription(String gDescription) {
        this.gDescription = gDescription == null ? null : gDescription.trim();
    }

    public Integer getgState() {
        return gState;
    }

    public void setgState(Integer gState) {
        this.gState = gState;
    }
}