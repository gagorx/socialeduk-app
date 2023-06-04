package com.example.socialeduk.views.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.socialeduk.R;
import com.example.socialeduk.views.feed.FeedActivity;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    public ArrayList<EventsContent> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Button home = findViewById(R.id.events_home);
        home.setOnClickListener(view -> home());


        eventsList = new ArrayList<>();

        RecyclerView eventRecycle = findViewById(R.id.recyclerView_events);

        eventsList.add(new EventsContent("27", "JULY", "15:00", "ANIVERSARIO LUCAS", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        eventsList.add(new EventsContent("31", "DEC", "19:00", "ANIVERSARIO ZE", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        eventsList.add(new EventsContent("24", "APR", "14:00", "ANIVERSARIO WESLEY", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        eventsList.add(new EventsContent("12", "JAN", "19:00", "ANIVERSARIO FRIZO", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Cras pulvinar tortor sed diam lobortis convallis. Sed in quam nibh. Suspendisse a ligula quam. Cras faucibus felis vehicula ligula consequat, " +
                "sit amet suscipit enim eleifend. Pellentesque eu tempor tortor, at varius mi. Nam nisl leo, egestas sed cursus et, aliquet sed libero. " +
                "Donec elementum metus eu nibh varius"));

        EventsAdapter eventsAdapter = new EventsAdapter(eventsList);

        eventRecycle.setAdapter(eventsAdapter);
        eventRecycle.setLayoutManager(new LinearLayoutManager(this));


    }

    private void home() {
        startActivity(new Intent(this, FeedActivity.class));
        finish();
    }
}