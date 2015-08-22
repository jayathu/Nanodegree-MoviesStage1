package com.udacity.nanodegree.showtime;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jnagaraj on 8/17/15.
 */
public class ImageItem implements Parcelable{

    String movie_title;
    String posterURL;
    String release_date;
    String synopsis;
    String vote_average;

    public ImageItem(String movie_title,
                     String posterURL,
                     String release_date,
                     String synopsis,
                     String vote_average)
    {
        this.movie_title = movie_title;
        this.posterURL = posterURL;
        this.release_date = release_date;
        this.synopsis = synopsis;
        this.vote_average = vote_average;
    }

    protected ImageItem(Parcel in) {
        movie_title = in.readString();
        posterURL = in.readString();
        release_date = in.readString();
        synopsis = in.readString();;
        vote_average = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movie_title);
        dest.writeString(posterURL);
        dest.writeString(release_date);
        dest.writeString(synopsis);
        dest.writeString(vote_average);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ImageItem> CREATOR = new Parcelable.Creator<ImageItem>() {

        @Override
        public ImageItem createFromParcel(Parcel in) {
            return new ImageItem(in);
        }

        @Override
        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };
}