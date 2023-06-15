package com.example.socialeduk.views.feed.recycle_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialeduk.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private ArrayList<PostContent> arrayList;

    public PostAdapter(ArrayList<PostContent> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_textfeed,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostContent feed =  arrayList.get(position);

        holder.profileName.setText(feed.getProfileName());
        holder.profileIcon.setImageResource(feed.getProfileIcon());
        holder.content.setText(feed.getContent());
        holder.createdAt.setText(feed.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView profileIcon;
        TextView profileName;
        TextView content;

        TextView createdAt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileIcon = itemView.findViewById(R.id.feed_profileIcon_imageView);
            profileName = itemView.findViewById(R.id.feed_profileName_textView);
            content = itemView.findViewById(R.id.feed_descriptionPost_textView);
            createdAt = itemView.findViewById(R.id.feed_createdTime_textView);
        }
    }
}
