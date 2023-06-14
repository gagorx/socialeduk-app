package com.example.socialeduk.views.groups;

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
import com.example.socialeduk.views.events.EventsActivity;
import com.example.socialeduk.views.feed.FeedActivity;
import com.example.socialeduk.views.friendsinvite.FriendsInviteActivity;
import com.example.socialeduk.views.login.LoginActivity;
import com.example.socialeduk.views.searchfriends.SearchFriendsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {

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

    private ArrayList<GroupsContent> arrayList;

    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        userPreferences = new UserPreferences(this);

        //botoes animados e textView
        logout = findViewById(R.id.groups_logout_button);
        logout.setOnClickListener(view -> _logout());

        options = findViewById(R.id.groups_options_button);
        options.setOnClickListener(view -> onAddButtonClicked());

        search = findViewById(R.id.groups_search_button);
        search.setOnClickListener(view -> startSearchAcivity());

        friendRequests = findViewById(R.id.groups_friendRequest_button);
        friendRequests.setOnClickListener(view -> startFriendsRequestsActivity());

        TextView nameUserLogged = findViewById(R.id.groups_nameUserLogged_textView);
        nameUserLogged.setText(userPreferences.getUsername());

        TextView emailUserLogged = findViewById(R.id.groups_emailUserLogged_textView);
        emailUserLogged.setText(userPreferences.getEmail());


        //animacoes
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        Button home = findViewById(R.id.groups_home_button);
        home.setOnClickListener(view -> startHome());

        Button events = findViewById(R.id.groups_events_button);
        events.setOnClickListener(view -> startEvents());

        arrayList = new ArrayList<>();

        RecyclerView groups = findViewById(R.id.groupsActivity_groupsRecycle);

        arrayList.add(new GroupsContent(R.drawable.wesley, "WesleyBerg Fãs", "Grupo de fans do wesley fantasiado de walter white"));
        arrayList.add(new GroupsContent(R.drawable.miquelino2, "Amigos do Miquelino", "Grupo de amigos do professor miquelino, aqui só rola churrasco e whyskey 😎😎😎"));
        arrayList.add(new GroupsContent(R.drawable.tartarugas, "Grupo das Tartarugas Ninja", "nao deixe o mestre splinter saber 🐀🐢🐢🐢🐢"));



        GroupsAdapter groupsAdapter = new GroupsAdapter(arrayList);

        groups.setAdapter(groupsAdapter);
        groups.setLayoutManager(new LinearLayoutManager(this));
    }

    private void startHome() {
        startActivity(new Intent(this, FeedActivity.class));
        finish();
    }

    private void startEvents() {
        startActivity(new Intent(this, EventsActivity.class));
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