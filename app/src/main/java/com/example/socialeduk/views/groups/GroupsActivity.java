package com.example.socialeduk.views.groups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.socialeduk.R;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {

    private ArrayList<GroupsContent> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_groups);


    }
}