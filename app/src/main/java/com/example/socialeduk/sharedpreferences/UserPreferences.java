package com.example.socialeduk.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.socialeduk.models.entities.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class UserPreferences {
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USER = "userJson";

    private SharedPreferences sharedPreferences;

    public UserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUserJson(String json) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, json);
        editor.apply();
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, null);
        editor.apply();
    }

    public String getUserJson() {
        return sharedPreferences.getString(KEY_USER, null);
    }

    public Long getId() {
        String json = getUserJson();
        if (json != null) {
            try {
                JSONObject userJson = new JSONObject(json);
                return userJson.getLong("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getUsername() {
        String json = getUserJson();
        if (json != null) {
            try {
                JSONObject userJson = new JSONObject(json);
                return userJson.getString("username");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getName() {
        String json = getUserJson();
        if (json != null) {
            try {
                JSONObject userJson = new JSONObject(json);
                return userJson.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getPassword() {
        String json = getUserJson();
        if (json != null) {
            try {
                JSONObject userJson = new JSONObject(json);
                return userJson.getString("password");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getEmail() {
        String json = getUserJson();
        if (json != null) {
            try {
                JSONObject userJson = new JSONObject(json);
                return userJson.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}