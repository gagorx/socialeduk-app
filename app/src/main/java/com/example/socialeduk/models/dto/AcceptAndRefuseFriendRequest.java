package com.example.socialeduk.models.dto;

public class AcceptAndRefuseFriendRequest {
    private Long userId;
    private Long friendRequestId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendRequestId() {
        return friendRequestId;
    }

    public void setFriendRequestId(Long friendRequestId) {
        this.friendRequestId = friendRequestId;
    }
}
