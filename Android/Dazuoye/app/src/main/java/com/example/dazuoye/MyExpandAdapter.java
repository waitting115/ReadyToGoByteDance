package com.example.dazuoye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandAdapter extends BaseExpandableListAdapter {
    private String[] name;
    private String[][] user;
//    private String[] backStory;
//    private int[] backgroundimg;

    LayoutInflater inflater;

    public MyExpandAdapter(Context context, String[][] user, String[] name) {
        this.inflater = LayoutInflater.from(context);
        this.name = name;
        this.user = user;
//        this.backStory = backStory;
//        this.backgroundimg = backgroundimg;
    }

    @Override
    public int getGroupCount() {
        return name.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return user[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return name[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return user[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.group_item,null);
        TextView textView = convertView.findViewById(R.id.textView4);
        textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.child_item, null);
        TextView nickTextView = convertView.findViewById(R.id.textView7);
        nickTextView.setText(getChild(groupPosition,childPosition).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
