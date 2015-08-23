package com.udacity.nanodegree.showtime;

import android.app.Activity;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jnagaraj on 8/17/15.
 */
public class GridViewAdapter extends ArrayAdapter<ImageItem> {

    private final String LOG_TAG = PopularMoviesFragment.class.getSimpleName();

    private ArrayList<ImageItem> mImageItems = new ArrayList<>();

    public GridViewAdapter(Activity context, List<ImageItem> imageItems)
    {
        super(context, 0, imageItems);
    }

    public void setGridData(ArrayList<ImageItem> imageItems)
    {
        this.mImageItems = imageItems;
        Log.v(LOG_TAG, "setGridData::length = " + imageItems.toArray().length);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.grid_item_layout_showtime, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = mImageItems.get(position);
        Picasso.with(getContext()).load(item.posterURL).into(holder.image);
        return row;
    }

    static class ViewHolder {
        ImageView image;
    }
}