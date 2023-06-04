package com.example.socialeduk.views.friendsinvite;

import android.widget.Button;
import android.widget.ImageButton;

public class FriendsInviteContent {

    public int icon;
    public String name;
    public String email;
    public ImageButton acceptFriend;
    public ImageButton blockFriend;
    public ImageButton refuseFriend;

    public FriendsInviteContent(String friendName, String friendEmail) {
        this.name = friendName;
        this.email = friendEmail;
    }

    public void setFriendIcon(int friendIcon) {
        this.icon = friendIcon;
    }

    public int getFriendIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void acceptInvite(){


    }

    public void refuseInvite(){

    }

    public void blockInvite(){

    }
}
