package com.carpool.dto;

import com.carpool.entity.Comment;
import com.carpool.entity.User;

/**
 * @author sunLei on 2019/7/15 23:01
 * @version 1.0
 */
public class CommentInfo {
    private Comment comment;
    private User user;

    public CommentInfo() {
    }

    public CommentInfo(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
