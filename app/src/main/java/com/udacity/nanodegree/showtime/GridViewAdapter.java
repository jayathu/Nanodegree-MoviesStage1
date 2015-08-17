package com.udacity.nanodegree.showtime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jnagaraj on 8/17/15.
 */
public class GridViewAdapter extends ArrayAdapter<ImageItem> {

    public GridViewAdapter(Activity context, List<ImageItem> imageItems)
    {
        super(context, 0, imageItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //View rootView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_layout_showtime, parent, false);

        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.grid_item_layout_showtime, parent, false);
            holder = new ViewHolder();
            //holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem imageItem = getItem(position);
        //holder.imageTitle.setText(imageItem.title);
        holder.image.setImageResource(imageItem.image);
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}