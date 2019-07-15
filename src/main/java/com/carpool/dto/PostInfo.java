package com.carpool.dto;

import com.carpool.entity.Post;
import com.carpool.entity.User;

/**
 * @author sunLei on 2019/7/15 23:00
 * @version 1.0
 */
public class PostInfo {
    private Post post;
    private User user;

    public PostInfo() {
    }

    public PostInfo(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
