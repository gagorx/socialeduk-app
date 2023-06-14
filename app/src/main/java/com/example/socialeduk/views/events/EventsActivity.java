package com.example.socialeduk.views.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialeduk.R;
import com.example.socialeduk.sharedpreferences.UserPreferences;
import com.example.socialeduk.views.feed.FeedActivity;
import com.example.socialeduk.views.friendsinvite.FriendsInviteActivity;
import com.example.socialeduk.views.groups.GroupsActivity;
import com.example.socialeduk.views.login.LoginActivity;
import com.example.socialeduk.views.searchfriends.SearchFriendsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

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

    UserPreferences userPreferences;

    public ArrayList<EventsContent> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        userPreferences = new UserPreferences(this);

        //botoes animados e textView
        logout = findViewById(R.id.events_logout_button);
        logout.setOnClickListener(view -> _logout());

        options = findViewById(R.id.events_options_button);
        options.setOnClickListener(view -> onAddButtonClicked());

        search = findViewById(R.id.events_search_button);
        search.setOnClickListener(view -> startSearchAcivity());

        friendRequests = findViewById(R.id.events_friendRequest_button);
        friendRequests.setOnClickListener(view -> startFriendsRequestsActivity());

        TextView nameUserLogged = findViewById(R.id.events_nameUserLogged_textView);
        nameUserLogged.setText(userPreferences.getUsername());

        TextView emailUserLogged = findViewById(R.id.events_emailUserLogged_textView);
        emailUserLogged.setText(userPreferences.getEmail());


        //animacoes
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);



        Button home = findViewById(R.id.events_home);
        home.setOnClickListener(view -> home());

        Button groups = findViewById(R.id.events_group_button);
        groups.setOnClickListener(view -> startGroups());


        eventsList = new ArrayList<>();

        RecyclerView eventRecycle = findViewById(R.id.recyclerView_events);

        eventsList.add(new EventsContent("27", "JULY", "15:00", "ANIVERSARIO LUCAS", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        eventsList.add(new EventsContent("31", "DEC", "19:00", "ANIVERSARIO ZE", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        eventsList.add(new EventsContent("24", "APR", "14:00", "ANIVERSARIO WESLEY", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        eventsList.add(new EventsContent("12", "JAN", "19:00", "ANIVERSARIO FRIZO", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        EventsAdapter eventsAdapter = new EventsAdapter(eventsList);

        eventRecycle.setAdapter(eventsAdapter);
        eventRecycle.setLayoutManager(new LinearLayoutManager(this));


    }

    private void startGroups() {
        startActivity(new Intent(this, GroupsActivity.class));
        finish();
    }

    private void home() {
        startActivity(new Intent(this, FeedActivity.class));
        finish();
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
            search.setEnabled(true);
            friendRequests.setVisibility(View.VISIBLE);
            friendRequests.setEnabled(true);
            logout.setVisibility(View.VISIBLE);
            logout.setEnabled(true);
        }else{
            search.setVisibility(View.INVISIBLE);
            search.setEnabled(false);
            friendRequests.setVisibility(View.INVISIBLE);
            friendRequests.setEnabled(false);
            logout.setVisibility(View.INVISIBLE);
            logout.setEnabled(false);
        }
    }


    private void startFriendsRequestsActivity() {
        clicked = true;
        onAddButtonClicked();
        startActivity(new Intent(this, FriendsInviteActivity.class));
    }

    private void startSearchAcivity() {
        clicked = true;
        onAddButtonClicked();
        startActivity(new Intent(this, SearchFriendsActivity.class));
    }

    private void _logout() {
        userPreferences.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}