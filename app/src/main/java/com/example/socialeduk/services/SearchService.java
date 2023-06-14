package com.example.socialeduk.services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.socialeduk.apienv.APIEnv;
import com.example.socialeduk.interfaces.VolleyCallBack;

import org.json.JSONException;

public class SearchService {

    public APIEnv env = new APIEnv();
    private RequestQueue _queue;

    public SearchService(RequestQueue queue) {
        this._queue = queue;
    }


    public void searchFriendsByName(Long userId, String name, VolleyCallBack callback)throws JSONException {

        String url = env.getURI() + "/users/getNotBlockedUsers/" + userId + "?name=" + name;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        _queue.add(stringRequest);

    }
}
