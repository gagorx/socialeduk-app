package com.example.socialeduk.views.feed;

public class FeedContent {

    public int profileIcon;
    public String profileName;
    private int postImage;
    private String message;

    public FeedContent(int profileIcon, String profileName, int postImage, String message) {
        this.profileIcon = profileIcon;
        this.profileName = profileName;
        this.postImage = postImage;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
