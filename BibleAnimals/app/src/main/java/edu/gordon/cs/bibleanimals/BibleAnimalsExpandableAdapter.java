package edu.gordon.cs.bibleanimals;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

/**
 * Expandable adapter to be used for the Bible animals app
 */
public class BibleAnimalsExpandableAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    private ArrayList<Object> childItems;
    private LayoutInflater inflater;
    private ArrayList<String> parentItems, child;
    private boolean main;
    private boolean about;
    private Context context;


    /**
     *
     * @param parents the parents in the list
     * @param children the children of the parents in the list
     * @param main whether or not this is the list of animals in MainActivity
     * @param about whether or not this is the list of animals in the AboutActivity
     * @param context the context the app is running in
     */
    public BibleAnimalsExpandableAdapter(ArrayList<String> parents, ArrayList<Object> children, boolean main, boolean about, Context context) {
        this.parentItems = parents;
        this.childItems = children;
        this.main = main;
        this.about = about;
        this.context = context;
    }

    /**
     * Set the inflater
     * @param inflater the inflater
     * @param activity the current activity
     */
    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {

        child = (ArrayList<String>) childItems.get(groupPosition);

        TextView textView = null;

        if (convertView == null) {
            int resource;
            if(main) resource = R.layout.child_main;
            else if(about) resource = R.layout.child_about;
            else resource = R.layout.child_verses;
            convertView = inflater.inflate(resource, null);
        }

        textView = (TextView) convertView.findViewById(R.id.childTextView);
        if(about) textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(Html.fromHtml(child.get(childPosition)));
        final String me = child.get(childPosition);

        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!about) {
                    // Verses on click
                    if (!main) {
                        try {
                            String verse = parentItems.get(groupPosition);
                            verse = verse.substring(0, verse.indexOf(":"));
                            Uri webpage = Uri.parse("https://www.biblegateway.com/passage/?version=LEB&" +
                                    "search=" + verse);
                            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                            activity.startActivity(webIntent);
                        } catch (Error e) {
                            // Do nothing, it is ok if the user does not have a browser
                        }
                        Toast.makeText(activity, parentItems.get(groupPosition),
                                Toast.LENGTH_SHORT).show();
                    }
                    // Main menu on click
                    else {
                        // Clear the heap
                        ArrayList<Animal> a = Animals.getInstance().getValues(Animals.getInstance().get());
                        for (int i = 0; i < a.size(); i++) {
                            a.get(i).clear();
                        }
                        Intent intent = new Intent(context, AnimalActivity.class);
                        intent.putExtra(MainActivity.ANIMAL, me);
                        context.startActivity(intent);
                    }
                }
            }
        });

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group, null);
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