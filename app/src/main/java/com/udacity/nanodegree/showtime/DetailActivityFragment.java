package com.udacity.nanodegree.showtime;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        if(intent != null)
        {

            ImageItem movie_details = intent.getParcelableExtra("movie_details");

            Log.v(LOG_TAG, "movie title:" + movie_details.movie_title);
            Log.v(LOG_TAG, "posterURL:" + movie_details.posterURL);
            Log.v(LOG_TAG, "release date:" + movie_details.release_date);
            Log.v(LOG_TAG, "synopsis: " + movie_details.synopsis);
            Log.v(LOG_TAG, "vote average:" + movie_details.vote_average);

            ((TextView)rootView.findViewById(R.id.detail_movie_title)).setText(movie_details.movie_title);
            ((TextView)rootView.findViewById(R.id.detail_release_date)).setText(movie_details.release_date);
            ((TextView)rootView.findViewById(R.id.detail_movie_synopsis)).setText(movie_details.synopsis);
            ((TextView)rootView.findViewById(R.id.detail_vote_average)).setText(movie_details.vote_average);


            ImageView holder = ((ImageView)rootView.findViewById(R.id.detail_poster));
            Picasso.with(getActivity()).load(movie_details.posterURL).into(holder);


        }

        return rootView;
    }
}
