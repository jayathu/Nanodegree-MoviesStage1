package com.udacity.nanodegree.showtime;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private GridView gridView;
    private GridViewAdapter gridViewAdapter;

    ImageItem[] imageItems = {
            new ImageItem("Image 0", R.drawable.sample_0),
            new ImageItem("Image 1", R.drawable.sample_1),
            new ImageItem("Image 2", R.drawable.sample_2),
            new ImageItem("Image 3", R.drawable.sample_3),
            new ImageItem("Image 4", R.drawable.sample_4),
            new ImageItem("Image 5", R.drawable.sample_5),
            new ImageItem("Image 6", R.drawable.sample_6),
            new ImageItem("Image 0", R.drawable.sample_0),
            new ImageItem("Image 1", R.drawable.sample_1),
            new ImageItem("Image 2", R.drawable.sample_2),
            new ImageItem("Image 3", R.drawable.sample_3),
            new ImageItem("Image 4", R.drawable.sample_4),
            new ImageItem("Image 5", R.drawable.sample_5),
            new ImageItem("Image 6", R.drawable.sample_6),
            new ImageItem("Image 0", R.drawable.sample_0),
            new ImageItem("Image 1", R.drawable.sample_1),
            new ImageItem("Image 2", R.drawable.sample_2),
            new ImageItem("Image 3", R.drawable.sample_3),
            new ImageItem("Image 4", R.drawable.sample_4),
            new ImageItem("Image 5", R.drawable.sample_5),
            new ImageItem("Image 6", R.drawable.sample_6)


    };
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        gridViewAdapter = new GridViewAdapter(getActivity(), Arrays.asList(imageItems));

        gridView = (GridView) rootView.findViewById(R.id.gridview_showtime);

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String msg = gridViewAdapter.getItem(position).title;
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }

        });

        return rootView;
    }

}
