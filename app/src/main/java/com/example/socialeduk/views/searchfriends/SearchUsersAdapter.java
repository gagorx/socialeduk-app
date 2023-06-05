package com.example.socialeduk.views.searchfriends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialeduk.R;
import com.example.socialeduk.views.feed.FeedAdapter;
import com.example.socialeduk.views.feed.FeedContent;

import java.util.ArrayList;

public class SearchUsersAdapter extends RecyclerView.Adapter<SearchUsersAdapter.ViewHolder> {
    private ArrayList<SearchUserContent> arrayList;

    public SearchUsersAdapter(ArrayList<SearchUserContent> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public SearchUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_search_users_result,parent,false);
        return new SearchUsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUsersAdapter.ViewHolder holder, int position) {
        SearchUserContent user =  arrayList.get(position);

        //holder.friendIcon.setImageResource(user.friendIcon);
        holder.friendName.setText(user.getFriendName());
        holder.friendEmail.setText(user.getFriendEmail());
        //holder.add.setOnClickListener(feed.getPostImage());
        //holder.block.setOnClickListener(feed.getMessage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView friendIcon;
        TextView friendName;
        TextView friendEmail;
        ImageButton add;
        ImageButton block;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendIcon = itemView.findViewById(R.id.layout_resultSearchUsers_icon);
            friendName = itemView.findViewById(R.id.layout_resultSearchUsers_name);
            friendEmail = itemView.findViewById(R.id.layout_resultSearchUsers_email);
            add = itemView.findViewById(R.id.layout_resultSearchUsers_addButton);
            block = itemView.findViewById(R.id.layout_resultSearchUsers_blockButton);
        }
    }
}
