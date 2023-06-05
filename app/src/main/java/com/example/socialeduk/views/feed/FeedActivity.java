package com.example.socialeduk.views.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.Post;
import com.example.socialeduk.services.PostService;
import com.example.socialeduk.sharedpreferences.UserPreferences;
import com.example.socialeduk.views.events.EventsActivity;
import com.example.socialeduk.views.friendsinvite.FriendsInviteActivity;
import com.example.socialeduk.views.groups.GroupsActivity;
import com.example.socialeduk.views.login.LoginActivity;
import com.example.socialeduk.views.searchfriends.SearchFriendsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

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

    UserPreferences user;
    PostService postService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        //services
        user = new UserPreferences(FeedActivity.this);
        postService = new PostService(Volley.newRequestQueue(this));

        //botoes animados e textView
        logout = findViewById(R.id.feed_logout_button);
        logout.setOnClickListener(view -> _logout());

        options = findViewById(R.id.feed_options_button);
        options.setOnClickListener(view -> onAddButtonClicked());

        search = findViewById(R.id.feed_search_button);
        search.setOnClickListener(view -> startSearchAcivity());

        friendRequests = findViewById(R.id.feed_friendRequest_button);
        friendRequests.setOnClickListener(view -> startFriendsRequestsActivity());

        TextView nameUserLogged = findViewById(R.id.feed_nameUserLogged_textView);
        nameUserLogged.setText(user.getUsername());

        TextView emailUserLogged = findViewById(R.id.feed_emailUserLogged_textView);
        emailUserLogged.setText(user.getEmail());


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

        Button createPost = findViewById(R.id.feed_createPost_button);
        createPost.setOnClickListener(view -> createPost(getPostcontent()));

        //feed
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
        user.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void createPost(String content) {

        Post post = new Post(user.getId(), content);

            try{
                postService.createPost(post, new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(FeedActivity.this, "Post realizado com sucesso!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(FeedActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                });
            }catch (JSONException e){
                Toast.makeText(FeedActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                        "persistir, contate o administrador", Toast.LENGTH_LONG).show();
            }
        }

    private String getPostcontent(){
        EditText postContent = findViewById(R.id.feed_postContent_editText);
        return postContent.getText().toString();
    }
}


