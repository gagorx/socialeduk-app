package com.example.socialeduk.services;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.socialeduk.apienv.APIEnv;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.BlockAndSendFriendRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class UserService {

    public APIEnv env = new APIEnv();
    private RequestQueue _queue;

    public UserService(RequestQueue queue) {
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

    public void sendFriendRequest(BlockAndSendFriendRequest request, VolleyCallBack callback)throws JSONException {
        String url = env.getURI() + "/users/sendFriendRequest";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("sender", request.getSender());
        jsonBody.put("receiver", request.getReceiver());

        final String json = jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return json.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding. Bytes of %s using %s", json, "utf-8");
                    return null;
                }
            }

        };
        _queue.add(stringRequest);
    }

    public void blockUser(BlockAndSendFriendRequest request, VolleyCallBack callback)throws JSONException {
        String url = env.getURI() + "/users/blockUser";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("sender", request.getSender());
        jsonBody.put("receiver", request.getReceiver());

        final String json = jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return json.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding. Bytes of %s using %s", json, "utf-8");
                    return null;
                }
            }

        };
        _queue.add(stringRequest);
    }
}
