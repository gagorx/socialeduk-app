package com.example.socialeduk.models.dto;

public class Post {
    private Long userId;
    private String Content;

    public Post(Long userId, String content) {
        this.userId = userId;
        Content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


}
