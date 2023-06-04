package com.example.socialeduk.views.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.socialeduk.R;
import com.example.socialeduk.views.events.EventsActivity;
import com.example.socialeduk.views.friendsinvite.FriendsInviteActivity;
import com.example.socialeduk.views.groups.GroupsActivity;
import com.example.socialeduk.views.login.LoginActivity;
import com.example.socialeduk.views.searchfriends.SearchFriendsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {

    //arrayList para o feed
    private ArrayList<FeedContent> arrayList;

    //botoes e animacoes
    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;
    private FloatingActionButton logout;
    private FloatingActionButton options;
    private FloatingActionButton search;
    private FloatingActionButton friendRequests;
    private boolean clicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feed);

        //botoes animados
        logout = findViewById(R.id.feed_logout_button);
        logout.setOnClickListener(view -> _logout());

        options = findViewById(R.id.feed_options_button);
        options.setOnClickListener(view -> onAddButtonClicked());

        search = findViewById(R.id.feed_search_button);
        search.setOnClickListener(view -> startSearchAcivity());

        friendRequests = findViewById(R.id.feed_friendRequest_button);
        friendRequests.setOnClickListener(view -> startFriendsRequestsActivity());


        //animacoes
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        //botoes feed
        Button events = findViewById(R.id.feed_events_button);
        events.setOnClickListener(view -> startEvents());

        Button groups = findViewById(R.id.feed_group_button);
        groups.setOnClickListener(view -> startGroups());


        arrayList = new ArrayList<>();

        RecyclerView feed = findViewById(R.id.feedActivity_feed_recicleView);

        arrayList.add(new FeedContent(R.drawable.ic_profile_background,"gagagago", R.drawable.ic_launcher_background, "testeeee"));
        arrayList.add(new FeedContent(R.drawable.ic_profile_background,"gagagago", R.drawable.kid, "testeeee"));
        arrayList.add(new FeedContent(R.drawable.ic_profile_background,"gagagago", R.drawable.kid, "testeeee"));

        FeedAdapter feedAdapter = new FeedAdapter(arrayList);

        feed.setAdapter(feedAdapter);
        feed.setLayoutManager(new LinearLayoutManager(this));

    }


   private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        if(!clicked){
            clicked = true;
        }else{
            clicked = false;
        }
    }

    private void setAnimation(boolean clicked) {
        if (!clicked) {
            search.startAnimation(fromBottom);
            friendRequests.startAnimation(fromBottom);
            logout.startAnimation(fromBottom);
            options.startAnimation(rotateOpen);
        } else {
            search.startAnimation(toBottom);
            friendRequests.startAnimation(toBottom);
            logout.startAnimation(toBottom);
            options.startAnimation(rotateClose);
        }
    }

    private void setVisibility(boolean clicked) {
       if (!clicked){
            search.setVisibility(View.VISIBLE);
            friendRequests.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
        }else{
            search.setVisibility(View.INVISIBLE);
            friendRequests.setVisibility(View.INVISIBLE);
            logout.setVisibility(View.INVISIBLE);
       }
    }

    private void startGroups() {
        startActivity(new Intent(this, GroupsActivity.class));
        finish();
    }

    private void startEvents(){
        startActivity(new Intent(this, EventsActivity.class));
        finish();
    }

    private void startFriendsRequestsActivity() {
        startActivity(new Intent(this, FriendsInviteActivity.class));
        finish();
    }

    private void startSearchAcivity() {
        startActivity(new Intent(this, SearchFriendsActivity.class));
        finish();
    }

    private void _logout() {
//        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}