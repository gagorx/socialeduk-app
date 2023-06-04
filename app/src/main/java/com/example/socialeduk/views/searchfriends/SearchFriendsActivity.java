package com.example.socialeduk.views.searchfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.socialeduk.R;
import com.example.socialeduk.views.feed.FeedActivity;

public class SearchFriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);

        ImageButton back = findViewById(R.id.searchFriends_back_button);
        back.setOnClickListener(v -> startFeed());

        ImageButton home = findViewById(R.id.searchFriends_home_button);
        home.setOnClickListener(v -> startFeed());
    }

    private void startFeed() {
        startActivity(new Intent(this, FeedActivity.class));
    }
}