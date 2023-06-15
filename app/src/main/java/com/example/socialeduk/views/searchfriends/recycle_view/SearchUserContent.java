package com.example.socialeduk.views.searchfriends.recycle_view;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

public class SearchUserContent {

    public Long friendId;

    public Long myId;
    public int friendIcon;
    public String friendName;
    public String friendEmail;
    public ImageButton addFriend;
    public ImageButton blockFriend;
    public Context context;
    public SearchUserContent(String friendName, String friendEmail, Long myId,Long friendId, Context context) {
        this.friendName = friendName;
        this.friendEmail = friendEmail;
        this.friendId = friendId;
        this.myId = myId;
        this.context = context;
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

    public Long getFriendId() {
        return friendId;
    }

    public Long getMyId() {
        return myId;
    }

    public Context getContext() {
        return context;
    }

}
