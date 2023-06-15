package com.example.socialeduk.views.groups.recycle_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialeduk.R;

import java.util.ArrayList;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {


    private ArrayList<GroupsContent> arrayList;

    public GroupsAdapter(ArrayList<GroupsContent> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_groups,parent,false);
        return new GroupsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupsContent group =  arrayList.get(position);

        holder.iconGroup.setImageResource(group.getGroupIcon());
        holder.groupTitle.setText(group.getGroupName());
        holder.descriptionGroup.setText(group.getDescriptionGroup());
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iconGroup;
        TextView groupTitle;
        TextView descriptionGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iconGroup = itemView.findViewById(R.id.groups_image);
            groupTitle = itemView.findViewById(R.id.groups_name);
            descriptionGroup = itemView.findViewById(R.id.groups_description);
        }
    }
}
