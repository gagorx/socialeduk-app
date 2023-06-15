package com.example.socialeduk.views.friendsinvite.recycle_view;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;

public class FriendsInviteContent {

    public Long id;
    public Long myId;
    public int icon;
    public String name;
    public String email;
    public ImageButton acceptFriend;
    public ImageButton blockFriend;
    public ImageButton refuseFriend;
    public Context context;

    public FriendsInviteContent(Long id, Long myId, String friendName, String friendEmail, Context context) {
        this.id = id;
        this.myId = myId;
        this.name = friendName;
        this.email = friendEmail;
        this.context = context;
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


    public Long getMyId() {
        return myId;
    }

    public Context getContext() {
        return context;
    }

    public Long getId() {
        return id;
    }
}
