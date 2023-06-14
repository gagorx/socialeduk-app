package com.example.socialeduk.views.searchfriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.DefaultResponse;
import com.example.socialeduk.models.entities.User;
import com.example.socialeduk.services.SearchService;
import com.example.socialeduk.services.UserService;
import com.example.socialeduk.sharedpreferences.UserPreferences;
import com.example.socialeduk.views.feed.FeedActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import android.text.TextWatcher;

import java.util.ArrayList;

public class SearchFriendsActivity extends AppCompatActivity {

    private UserService userService;

    private SearchService searchService;

    private UserPreferences userPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);

        userPreferences = new UserPreferences(SearchFriendsActivity.this);
        userService = new UserService(Volley.newRequestQueue(this));
        searchService = new SearchService(Volley.newRequestQueue(this));

        ImageButton back = findViewById(R.id.searchFriends_back_button);
        back.setOnClickListener(v -> startFeed());

        ImageButton home = findViewById(R.id.searchFriends_home_button);
        home.setOnClickListener(v -> startFeed());

        EditText inputSearch = findViewById(R.id.searchFriends_inputSearch_editText);

        getAllUsers();

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchUsers(inputSearch.getText().toString());
            }
        });




    }

    private ArrayList<SearchUserContent> createSearchContentArray(DefaultResponse<ArrayList<User>> users) {
        ArrayList<SearchUserContent> searchContent = new ArrayList<>();
        ArrayList<User> listUser;
        listUser = users.getData();

        for(int i=0; i < listUser.size(); i++){
            if (!listUser.get(i).getId().toString().equals(userPreferences.getId().toString())){
                searchContent.add(new SearchUserContent(listUser.get(i).getUsername(), listUser.get(i).getEmail(), userPreferences.getId(), listUser.get(i).getId(), SearchFriendsActivity.this));
            }
        }


        return searchContent;
    }

    private void startFeed() {
        finish();
    }

    private void getAllUsers() {

        DefaultResponse<ArrayList<User>> userList = new DefaultResponse<>();
        try {
            userService.getAllUsers(new VolleyCallBack() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject obj = (JSONObject) new
                            JSONTokener(response).nextValue();

                    userList.setStatus(obj.getString("status"));
                    userList.setMessage(obj.getString("message"));
                    JSONArray usersJson = obj.getJSONArray("data");
                    ArrayList<User> userArrayList = new ArrayList<>();

                    for(int i = 0; i < usersJson.length(); i++){
                        JSONObject ob = new JSONObject(usersJson.get(i).toString());
                        User newUser = new User();
                        newUser.setId(ob.getLong("id"));
                        newUser.setUsername(ob.getString("username"));
                        newUser.setPassword(ob.getString("password"));
                        newUser.setName(ob.getString("name"));
                        newUser.setEmail(ob.getString("email"));
                        userArrayList.add(newUser);
                    }

                    userList.setData(userArrayList);

                    SearchUsersAdapter usersAdapter = new SearchUsersAdapter(createSearchContentArray(userList));

                    RecyclerView listUser = findViewById(R.id.searchFriends_listUsers_recicleView);
                    listUser.setAdapter(usersAdapter);
                    listUser.setLayoutManager(new LinearLayoutManager(SearchFriendsActivity.this));


                }catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(SearchFriendsActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                        "persistir, contate o administrador", Toast.LENGTH_LONG).show();
            }
        });
            }catch(JSONException e) {
                Toast.makeText(SearchFriendsActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
            }

    }

    private void searchUsers(String name) {

        DefaultResponse<ArrayList<User>> userList = new DefaultResponse<>();
        try {
            searchService.searchFriendsByName(userPreferences.getId(), name ,new VolleyCallBack() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject obj = (JSONObject) new
                                JSONTokener(response).nextValue();

                        userList.setStatus(obj.getString("status"));
                        userList.setMessage(obj.getString("message"));
                        JSONArray usersJson = obj.getJSONArray("data");
                        ArrayList<User> userArrayList = new ArrayList<>();

                        for(int i = 0; i < usersJson.length(); i++){
                            JSONObject ob = new JSONObject(usersJson.get(i).toString());
                            User newUser = new User();
                            newUser.setId(ob.getLong("id"));
                            newUser.setUsername(ob.getString("username"));
                            newUser.setPassword(ob.getString("password"));
                            newUser.setName(ob.getString("name"));
                            newUser.setEmail(ob.getString("email"));
                            userArrayList.add(newUser);
                        }

                        userList.setData(userArrayList);

                        SearchUsersAdapter usersAdapter = new SearchUsersAdapter(createSearchContentArray(userList));

                        RecyclerView listUser = findViewById(R.id.searchFriends_listUsers_recicleView);
                        listUser.setAdapter(usersAdapter);
                        listUser.setLayoutManager(new LinearLayoutManager(SearchFriendsActivity.this));


                    }catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(SearchFriendsActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                            "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                }
            });
        }catch(JSONException e) {
            Toast.makeText(SearchFriendsActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
        }

    }
}



