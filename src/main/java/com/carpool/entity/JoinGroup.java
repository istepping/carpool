package com.carpool.entity;

import java.util.Date;

public class JoinGroup {
    private Long jId;

    private Long gId;

    private Long uId;

    private Date jTime;

    private Integer jIdentity;

    private Integer jState;

    public JoinGroup() {
    }

    public JoinGroup(Long jId, Long gId, Long uId, Date jTime, Integer jIdentity, Integer jState) {
        this.jId = jId;
        this.gId = gId;
        this.uId = uId;
        this.jTime = jTime;
        this.jIdentity = jIdentity;
        this.jState = jState;
    }

    public JoinGroup(Long gId, Long uId, Date jTime, Integer jIdentity, Integer jState) {
        this.gId = gId;
        this.uId = uId;
        this.jTime = jTime;
        this.jIdentity = jIdentity;
        this.jState = jState;
    }

    public Long getjId() {
        return jId;
    }

    public void setjId(Long jId) {
        this.jId = jId;
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

    public Date getjTime() {
        return jTime;
    }

    public void setjTime(Date jTime) {
        this.jTime = jTime;
    }

    public Integer getjIdentity() {
        return jIdentity;
    }

    public void setjIdentity(Integer jIdentity) {
        this.jIdentity = jIdentity;
    }

    public Integer getjState() {
        return jState;
    }

    public void setjState(Integer jState) {
        this.jState = jState;
    }
}