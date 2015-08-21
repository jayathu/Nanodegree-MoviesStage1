package com.udacity.nanodegree.showtime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class PopularMoviesFragment extends Fragment {

    String[] movieId, movieTitle, movieReleaseDate, movieVoteAverage, movieOverview, moviePosterPath;

    private GridView gridView;
    private GridViewAdapter gridViewAdapter;

    //ArrayList<ImageItem> imageItemsCache = new ArrayList<>();

    public PopularMoviesFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovieData();
    }


    private void updateMovieData()
    {
        FetchPopularMovies moviesTask = new FetchPopularMovies();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sort_type = sharedPreferences.getString("sortby", "0");

        moviesTask.execute(sort_type);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        gridViewAdapter = new GridViewAdapter(getActivity(), new ArrayList<ImageItem>());

        gridView = (GridView) rootView.findViewById(R.id.gridview_showtime);

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String msg = gridViewAdapter.getItem(position).title;
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                ImageItem gridItem = gridViewAdapter.getItem(position);

                Intent intent = new Intent(getActivity(), DetailActivity.class)
                                                .putExtra(Intent.EXTRA_TEXT, gridItem.title);
                startActivity(intent);
            }

        });

        return rootView;
    }

    //Inner class to asynchronously fetch movie data from the server
    class FetchPopularMovies extends AsyncTask<String, Void, ArrayList<ImageItem>> {
        private final String LOG_TAG = FetchPopularMovies.class.getSimpleName();

        ArrayList<ImageItem> imageItemsCache = new ArrayList<>();
        InputStream inputStream;
        @Override
        protected ArrayList<ImageItem> doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJsonStr = null;


            String sort_by_clause = "";

            String popularity = "popularity.desc";
            String ratings = "vote_average.desc";


            if(params[0].equals("0"))
            {
                sort_by_clause = popularity;
            }
            else
            {
                sort_by_clause = ratings;
            }
            String api_key = "a21c54a991f730b8a3df5a3a6b33e941";

            try {

                Log.v("Sort type = ", sort_by_clause);


                final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
                final String FORMAT_PARAM = "sort_by";
                final String API = "api_key";

                Uri builtUrl = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(FORMAT_PARAM, sort_by_clause)
                        .appendQueryParameter(API, api_key)
                        .build();

                URL url  = new URL(builtUrl.toString());

                Log.v(LOG_TAG, "Built URL" + builtUrl.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Read the input stream into a String
                inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                movieJsonStr = buffer.toString();

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getMovieDataFromJson(movieJsonStr);
            } catch (JSONException j) {
                Log.e(LOG_TAG, "JSON Error", j);

            }

            Log.v(LOG_TAG , "Code never reaches here");

            return  null;

        }

        private ArrayList<ImageItem> getMovieDataFromJson(String forecastJsonStr)
                throws JSONException {

            final String BASE_URL = "http://image.tmdb.org/t/p/w185/";
            JSONObject movieJson = new JSONObject(forecastJsonStr);
            JSONArray movieArray = movieJson.getJSONArray("results");
            movieId = new String[movieArray.length()];
            movieTitle = new String[movieArray.length()];
            movieReleaseDate = new String[movieArray.length()];
            movieVoteAverage = new String[movieArray.length()];
            movieOverview = new String[movieArray.length()];
            moviePosterPath = new String[movieArray.length()];

            Log.v(LOG_TAG, movieArray.length() + "");
            //imageItemsCache.clear();
            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject movie = movieArray.getJSONObject(i);
                movieId[i] = movie.getString("id");
                movieTitle[i] = movie.getString("original_title");
                movieReleaseDate[i] = movie.getString("release_date");
                movieVoteAverage[i] = movie.getString("vote_average");
                movieOverview[i] = movie.getString("overview");
                moviePosterPath[i] = movie.getString("poster_path");
                ImageItem item = new ImageItem(movieTitle[i], 0);
                item.SetImagePath(BASE_URL+moviePosterPath[i]);
                imageItemsCache.add(item);
            }

            return imageItemsCache;
        }


        @Override
        protected void onPostExecute(ArrayList<ImageItem> imageItems) {

            if (imageItems != null) {

                gridViewAdapter.clear();

                for (ImageItem img : imageItems) {
                    Log.v(LOG_TAG, "Movies :: " + img.title);
                    gridViewAdapter.add(img);

                }
                gridViewAdapter.setGridData(imageItems);
            }
        }

    }
}
