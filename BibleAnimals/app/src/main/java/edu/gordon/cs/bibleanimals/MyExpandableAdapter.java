package edu.gordon.cs.bibleanimals;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private ArrayList<Object> childItems;
    private LayoutInflater inflater;
    private ArrayList<String> parentItems, child;

    public MyExpandableAdapter(ArrayList<String> parents, ArrayList<Object> children) {
        this.parentItems = parents;
        this.childItems = children;
    }

    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        child = (ArrayList<String>) childItems.get(groupPosition);

        TextView textView = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group, null);
        }

        textView = (TextView) convertView.findViewById(R.id.textView1);
        textView.setText(Html.fromHtml(child.get(childPosition)));

        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    String verse = parentItems.get(groupPosition);
                    verse = verse.substring(0, verse.indexOf(":"));
                    Uri webpage = Uri.parse("https://www.biblegateway.com/passage/?version=LEB&" +
                            "search=" + verse);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    activity.startActivity(webIntent);
                }
                catch (Error e) {
                    // Do nothing, it is ok if the user does not have a browser
                }
                Toast.makeText(activity, parentItems.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child, null);
        }

        ((CheckedTextView) convertView).setText(Html.fromHtml(parentItems.get(groupPosition)));
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) childItems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}