package com.example.socialeduk.views.events.recycle_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialeduk.R;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private ArrayList<EventsContent> arrayList;

    public EventsAdapter(ArrayList<EventsContent> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_events,parent,false);
        return new EventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {
        EventsContent event = arrayList.get(position);

        holder.day.setText(event.getDay());
        holder.month.setText(event.getMonth());
        holder.hour.setText(event.getHour());
        holder.nameEvent.setText(event.getTitleEvent());
        holder.descriptionEvent.setText(event.getDescriptionEvent());

    }

    @Override
    public int getItemCount() {return arrayList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView day;
        TextView month;
        TextView hour;
        TextView nameEvent;
        TextView descriptionEvent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.events_day);
            month = itemView.findViewById(R.id.events_month);
            hour = itemView.findViewById(R.id.events_hour);
            nameEvent = itemView.findViewById(R.id.groups_name);
            descriptionEvent = itemView.findViewById(R.id.groups_description);
        }
    }
}
