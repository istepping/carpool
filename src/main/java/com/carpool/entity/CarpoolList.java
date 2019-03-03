package com.carpool.entity;

import java.util.Date;

public class CarpoolList {
    private Long lId;

    private Long lCreateUserId;

    private String lStartPlace;

    private String lEndPlace;

    private Date lTime;

    private Integer lMaxNumber;

    private Integer lNumber;

    private String lCarpoolMode;

    private String lHasCar;

    private String lGenderRequirement;

    private Date lCreateTime;

    private String lExtra;

    private Integer lState;

    public CarpoolList() {
    }

    public CarpoolList(Long lId, Long lCreateUserId, String lStartPlace, String lEndPlace, Date lTime, Integer lMaxNumber, String lCarpoolMode, String lHasCar, String lGenderRequirement,String lExtra) {
        this.lId = lId;
        this.lCreateUserId = lCreateUserId;
        this.lStartPlace = lStartPlace;
        this.lEndPlace = lEndPlace;
        this.lTime = lTime;
        this.lMaxNumber = lMaxNumber;
        this.lNumber = lNumber;
        this.lCarpoolMode = lCarpoolMode;
        this.lHasCar = lHasCar;
        this.lGenderRequirement = lGenderRequirement;
        this.lCreateTime = lCreateTime;
        this.lExtra = lExtra;
        this.lState = lState;
    }

    public CarpoolList(Long lCreateUserId, String lStartPlace, String lEndPlace, Date lTime, Integer lMaxNumber, Integer lNumber, String lCarpoolMode, String lHasCar, String lGenderRequirement, Date lCreateTime, String lExtra, Integer lState) {
        this.lCreateUserId = lCreateUserId;
        this.lStartPlace = lStartPlace;
        this.lEndPlace = lEndPlace;
        this.lTime = lTime;
        this.lMaxNumber = lMaxNumber;
        this.lNumber = lNumber;
        this.lCarpoolMode = lCarpoolMode;
        this.lHasCar = lHasCar;
        this.lGenderRequirement = lGenderRequirement;
        this.lCreateTime = lCreateTime;
        this.lExtra = lExtra;
        this.lState = lState;
    }

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public Long getlCreateUserId() {
        return lCreateUserId;
    }

    public void setlCreateUserId(Long lCreateUserId) {
        this.lCreateUserId = lCreateUserId;
    }

    public String getlStartPlace() {
        return lStartPlace;
    }

    public void setlStartPlace(String lStartPlace) {
        this.lStartPlace = lStartPlace == null ? null : lStartPlace.trim();
    }

    public String getlEndPlace() {
        return lEndPlace;
    }

    public void setlEndPlace(String lEndPlace) {
        this.lEndPlace = lEndPlace == null ? null : lEndPlace.trim();
    }

    public Date getlTime() {
        return lTime;
    }

    public void setlTime(Date lTime) {
        this.lTime = lTime;
    }

    public Integer getlMaxNumber() {
        return lMaxNumber;
    }

    public void setlMaxNumber(Integer lMaxNumber) {
        this.lMaxNumber = lMaxNumber;
    }

    public Integer getlNumber() {
        return lNumber;
    }

    public void setlNumber(Integer lNumber) {
        this.lNumber = lNumber;
    }

    public String getlCarpoolMode() {
        return lCarpoolMode;
    }

    public void setlCarpoolMode(String lCarpoolMode) {
        this.lCarpoolMode = lCarpoolMode == null ? null : lCarpoolMode.trim();
    }

    public String getlHasCar() {
        return lHasCar;
    }

    public void setlHasCar(String lHasCar) {
        this.lHasCar = lHasCar == null ? null : lHasCar.trim();
    }

    public String getlGenderRequirement() {
        return lGenderRequirement;
    }

    public void setlGenderRequirement(String lGenderRequirement) {
        this.lGenderRequirement = lGenderRequirement == null ? null : lGenderRequirement.trim();
    }

    public Date getlCreateTime() {
        return lCreateTime;
    }

    public void setlCreateTime(Date lCreateTime) {
        this.lCreateTime = lCreateTime;
    }

    public String getlExtra() {
        return lExtra;
    }

    public void setlExtra(String lExtra) {
        this.lExtra = lExtra == null ? null : lExtra.trim();
    }

    public Integer getlState() {
        return lState;
    }

    public void setlState(Integer lState) {
        this.lState = lState;
    }
}