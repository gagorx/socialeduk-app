package com.example.socialeduk.models.dto;

import com.example.socialeduk.models.entities.User;

public class Post {
    private Long id;

    private String content;

    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
