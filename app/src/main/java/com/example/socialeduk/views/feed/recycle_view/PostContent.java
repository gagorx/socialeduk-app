package com.example.socialeduk.views.feed.recycle_view;

import java.sql.Timestamp;

public class PostContent {

    public int profileIcon;
    public String profileName;
    private int postImage;
    private String content;
    private String createdAt;

    public PostContent(int profileIcon, String profileName, String content, String createdAt) {
        this.profileIcon = profileIcon;
        this.profileName = profileName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(int profileIcon) {
        this.profileIcon = profileIcon;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getContent() {
        return content;
    }

    public void setMessage(String message) {
        this.content = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
