package com.example.socialeduk.services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.socialeduk.apienv.APIEnv;
import com.example.socialeduk.interfaces.VolleyCallBack;

import org.json.JSONException;

public class GetUserService {

    public APIEnv env = new APIEnv();
    private RequestQueue _queue;

    public GetUserService(RequestQueue queue) {
        this._queue = queue;
    }



    public void getAllUsers(VolleyCallBack callback)throws JSONException {

        String url = env.getURI() + "/users/getAll";

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

    public void getUserById(Long id, VolleyCallBack callback)throws JSONException {

        String url = env.getURI() + "/users/" + id;

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

    public void getFriends(Long id, VolleyCallBack callback)throws JSONException {

        String url = env.getURI() + "/users/getFriends/" + id;

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
