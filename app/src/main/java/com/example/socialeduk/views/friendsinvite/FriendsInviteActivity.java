package com.example.socialeduk.views.friendsinvite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.socialeduk.R;
import com.example.socialeduk.views.feed.FeedActivity;

public class FriendsInviteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_invite);

        ImageButton back = findViewById(R.id.friendsInvite_back_button);
        back.setOnClickListener(v -> startFeed());

        ImageButton home = findViewById(R.id.friendsInvite_home_button);
        home.setOnClickListener(v -> startFeed());

    }

    private void startFeed() {
        startActivity(new Intent(this, FeedActivity.class));
        finish();
    }
}