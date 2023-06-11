package com.example.socialeduk.views.feed;

public class PostContent {

    public int profileIcon;
    public String profileName;
    private int postImage;
    private String content;

    public PostContent(int profileIcon, String profileName, String content) {
        this.profileIcon = profileIcon;
        this.profileName = profileName;
        this.content = content;
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
}
