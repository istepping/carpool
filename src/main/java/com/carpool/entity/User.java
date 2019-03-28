package com.carpool.entity;

public class User {
    private Long uId;

    private String wxId;

    private String uNickName;

    private String uAvatarUrl;

    private String uGender;

    private String uCity;

    private String uProvince;

    private String uLanguage;

    private String uState;

    public User() {
    }

    public User(String wxId) {
        this.wxId = wxId;
    }

    public User(Long uId, String uNickName, String uAvatarUrl, String uGender, String uCity, String uProvince, String uLanguage, String uState) {
        this.uId = uId;
        this.uNickName = uNickName;
        this.uAvatarUrl = uAvatarUrl;
        this.uGender = uGender;
        this.uCity = uCity;
        this.uProvince = uProvince;
        this.uLanguage = uLanguage;
        this.uState = uState;
    }

    public User(String wxId, String uNickName, String uAvatarUrl, String uGender, String uCity, String uProvince, String uLanguage, String uState) {
        this.wxId = wxId;
        this.uNickName = uNickName;
        this.uAvatarUrl = uAvatarUrl;
        this.uGender = uGender;
        this.uCity = uCity;
        this.uProvince = uProvince;
        this.uLanguage = uLanguage;
        this.uState = uState;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    public String getuNickName() {
        return uNickName;
    }

    public void setuNickName(String uNickName) {
        this.uNickName = uNickName == null ? null : uNickName.trim();
    }

    public String getuAvatarUrl() {
        return uAvatarUrl;
    }

    public void setuAvatarUrl(String uAvatarUrl) {
        this.uAvatarUrl = uAvatarUrl == null ? null : uAvatarUrl.trim();
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender == null ? null : uGender.trim();
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity == null ? null : uCity.trim();
    }

    public String getuProvince() {
        return uProvince;
    }

    public void setuProvince(String uProvince) {
        this.uProvince = uProvince == null ? null : uProvince.trim();
    }

    public String getuLanguage() {
        return uLanguage;
    }

    public void setuLanguage(String uLanguage) {
        this.uLanguage = uLanguage == null ? null : uLanguage.trim();
    }

    public String getuState() {
        return uState;
    }

    public void setuState(String uState) {
        this.uState = uState == null ? null : uState.trim();
    }
}