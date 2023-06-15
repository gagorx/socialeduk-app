package com.example.socialeduk.views.groups.recycle_view;

public class GroupsContent {

    public int groupIcon;
    public String groupName;
    public String groupDescription;

    public GroupsContent(int groupIcon, String groupName, String descriptionGroup) {
        this.groupIcon = groupIcon;
        this.groupName = groupName;
        this.groupDescription = descriptionGroup;
    }


    public int getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(int groupIcon) {
        this.groupIcon = groupIcon;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescriptionGroup() {
        return groupDescription;
    }

    public void setDescriptionGroup(String descriptionGroup) {
        this.groupDescription = descriptionGroup;
    }



}
