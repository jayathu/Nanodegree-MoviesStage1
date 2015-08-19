package com.udacity.nanodegree.showtime;

import android.graphics.Bitmap;

/**
 * Created by jnagaraj on 8/17/15.
 */
public class ImageItem {

    String title;

    int imageIndex;

    String posterURL;

    public void SetImagePath(String stringURL)
    {
        posterURL = stringURL;
    }

    public String getPosterURL()
    {
        return posterURL;
    }

    public ImageItem(String title, int index)
    {
        this.title = title;
        this.imageIndex = index;
    }
}