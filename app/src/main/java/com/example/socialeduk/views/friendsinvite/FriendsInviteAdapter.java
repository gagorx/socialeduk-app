package com.example.socialeduk.views.friendsinvite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialeduk.R;
import com.example.socialeduk.views.feed.FeedAdapter;
import com.example.socialeduk.views.feed.FeedContent;

import java.util.ArrayList;

public class FriendsInviteAdapter {

    private ArrayList<FriendsInviteContent> arrayList;

    public FriendsInviteAdapter(ArrayList<FriendsInviteContent> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    //@Override
    public FriendsInviteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_friend_invitations_result,parent,false);
        return new FriendsInviteAdapter.ViewHolder(view);
    }

    //@Override
    public void onBindViewHolder(@NonNull FriendsInviteAdapter.ViewHolder holder, int position) {
        FriendsInviteContent invite =  arrayList.get(position);

        holder.friendName.setText(invite.getName());
        holder.friendEmail.setText(invite.getEmail());
        //holder.acceptFriend.setOnClickListener(invite.getPostImage());
        //holder.blockFriend.setOnClickListener(invite.getMessage());
        holder.refuseFriend.setOnClickListener(v -> invite.acceptInvite());
    }

    //@Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView friendName;
        TextView friendEmail;
        ImageButton acceptFriend;
        ImageButton blockFriend;
        ImageButton refuseFriend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendName = itemView.findViewById(R.id.layout_friendsInvite_name);
            friendEmail = itemView.findViewById(R.id.layout_friendsInvite_email);
            acceptFriend = itemView.findViewById(R.id.layout_friendsInvite_addButton);
            blockFriend = itemView.findViewById(R.id.layout_friendsInvite_blockButton);
            refuseFriend = itemView.findViewById(R.id.layout_friendsInvite_refuseButton);
        }
    }
}
