package com.example.socialeduk.views.friendsinvite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.DefaultResponse;
import com.example.socialeduk.models.entities.FriendRequest;
import com.example.socialeduk.models.entities.User;
import com.example.socialeduk.services.UserService;
import com.example.socialeduk.sharedpreferences.UserPreferences;
import com.example.socialeduk.views.feed.FeedActivity;
import com.example.socialeduk.views.searchfriends.SearchFriendsActivity;
import com.example.socialeduk.views.searchfriends.SearchUserContent;
import com.example.socialeduk.views.searchfriends.SearchUsersAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Map;

public class FriendsInviteActivity extends AppCompatActivity {

    private UserService userService;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_invite);

        userPreferences = new UserPreferences(FriendsInviteActivity.this);
        userService = new UserService(Volley.newRequestQueue(this));

        ImageButton back = findViewById(R.id.friendsInvite_back_button);
        back.setOnClickListener(v -> startFeed());

        ImageButton home = findViewById(R.id.friendsInvite_home_button);
        home.setOnClickListener(v -> startFeed());
        
        getAllFriendResquests();
    }

    private ArrayList<FriendsInviteContent> createFriendsInviteContentArray(DefaultResponse<ArrayList<FriendRequest>> invites) {
        ArrayList<FriendsInviteContent> invitesContent = new ArrayList<>();
        ArrayList<FriendRequest> listInvites;
        listInvites = invites.getData();

        for(int i=0; i < listInvites.size(); i++){
            invitesContent.add(new FriendsInviteContent(listInvites.get(i).getId(), userPreferences.getId(),
                    listInvites.get(i).getSender().getName(),listInvites.get(i).getSender().getEmail(), FriendsInviteActivity.this));

        }


        return invitesContent;
    }

    private void getAllFriendResquests() {

        DefaultResponse<ArrayList<FriendRequest>> friendRequestList = new DefaultResponse<>();
        try {
            userService.getAllReceivedFriendsRequests(userPreferences.getId(),new VolleyCallBack() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject obj = (JSONObject) new
                                JSONTokener(response).nextValue();

                        friendRequestList.setStatus(obj.getString("status"));
                        friendRequestList.setMessage(obj.getString("message"));
                        JSONArray friendResquetJSON = obj.getJSONArray("data");
                        ArrayList<FriendRequest> friendRequestArrayList = new ArrayList<>();

                        for(int i = 0; i < friendResquetJSON.length(); i++){
                            JSONObject ob = new JSONObject(friendResquetJSON.get(i).toString());
                            FriendRequest friendRequest = new FriendRequest();
                            JSONObject userSenderJSON;
                            JSONObject userReceiverJSON;
                            User userSender = new User();
                            User userReceiver = new User();

                            friendRequest.setId(ob.getLong("id"));

                            userSenderJSON = ob.getJSONObject("sender");
                            userSender.setId(userSenderJSON.getLong("id"));
                            userSender.setUsername(userSenderJSON.getString("username"));
                            userSender.setName(userSenderJSON.getString("name"));
                            userSender.setEmail(userSenderJSON.getString("email"));
                            friendRequest.setSender(userSender);

                            userReceiverJSON = ob.getJSONObject("receiver");
                            userReceiver.setId(userReceiverJSON.getLong("id"));
                            userReceiver.setUsername(userReceiverJSON.getString("username"));
                            userReceiver.setName(userReceiverJSON.getString("name"));
                            userReceiver.setEmail(userReceiverJSON.getString("email"));
                            friendRequest.setReceiver(userReceiver);

                            friendRequestArrayList.add(friendRequest);
                        }

                        friendRequestList.setData(friendRequestArrayList);

                        FriendsInviteAdapter inviteAdapter = new FriendsInviteAdapter(createFriendsInviteContentArray(friendRequestList));

                        RecyclerView listInvites = findViewById(R.id.friendsInvite_list);
                        listInvites.setAdapter(inviteAdapter);
                        listInvites.setLayoutManager(new LinearLayoutManager(FriendsInviteActivity.this));


                    }catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(FriendsInviteActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                            "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                }
            });
        }catch(JSONException e) {
            Toast.makeText(FriendsInviteActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
        }

    }

    private void startFeed() {
        startActivity(new Intent(this, FeedActivity.class));
        finish();
    }
}