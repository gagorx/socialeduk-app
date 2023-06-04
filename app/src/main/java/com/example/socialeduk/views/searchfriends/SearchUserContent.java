package com.example.socialeduk.views.searchfriends;

import android.widget.Button;
import android.widget.ImageButton;

public class SearchUserContent {

    public int friendIcon;
    public String friendName;
    public String friendEmail;
    public ImageButton addFriend;
    public ImageButton blockFriend;

    public SearchUserContent(String friendName, String friendEmail) {
        this.friendName = friendName;
        this.friendEmail = friendEmail;
    }

    public void setFriendIcon(int friendIcon) {
        this.friendIcon = friendIcon;
    }

    public int getFriendIcon() {
        return friendIcon;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void addFriend(){

    }

}
