package com.carpool.dto;

import java.util.Date;

/**
 * @author sunLei on 2019/3/30 15:48
 * @version 1.0
 * @apiNote
 */
public class MessageDto {
    private String gId;//群Id
    private String msgId;//消息ID,"msg"+id
    private String type;
    private String senderId;
    private String senderAvatar;
    private String senderNickName;
    private String sendTime;//消息具体时间戳
    private String msg;//消息内容

    public MessageDto() {
    }

    public MessageDto(String gId, String msgId, String type, String senderId, String senderAvatar, String senderNickName, String sendTime, String msg) {
        this.gId = gId;
        this.msgId = msgId;
        this.type = type;
        this.senderId = senderId;
        this.senderAvatar = senderAvatar;
        this.senderNickName = senderNickName;
        this.sendTime = sendTime;
        this.msg = msg;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public String getSenderNickName() {
        return senderNickName;
    }

    public void setSenderNickName(String senderNickName) {
        this.senderNickName = senderNickName;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
