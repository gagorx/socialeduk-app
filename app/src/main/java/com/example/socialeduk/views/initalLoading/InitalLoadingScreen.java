package com.example.socialeduk.views.initalLoading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.socialeduk.R;
import com.example.socialeduk.views.login.LoginActivity;

public class InitalLoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inital_loading_screen);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                    finish();
            }
        }, 5000);
    }
}