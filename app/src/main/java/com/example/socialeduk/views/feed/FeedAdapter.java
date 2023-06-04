package com.example.socialeduk.views.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialeduk.R;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {


    private ArrayList<FeedContent> arrayList;

    public FeedAdapter(ArrayList<FeedContent> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_feed,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedContent feed =  arrayList.get(position);

        holder.profileName.setText(feed.getProfileName());
        holder.profileIcon.setImageResource(feed.getProfileIcon());
        holder.postImage.setImageResource(feed.getPostImage());
        holder.message.setText(feed.getMessage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView profileIcon;
        ImageView postImage;
        TextView profileName;
        TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileIcon = itemView.findViewById(R.id.feed_profileIcon_imageView);
            postImage = itemView.findViewById(R.id.feed_postImage_imageView);
            profileName = itemView.findViewById(R.id.feed_profileName_textView);
            message = itemView.findViewById(R.id.feed_descriptionPost_textView);
        }
    }
}
