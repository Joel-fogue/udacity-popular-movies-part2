package main.android.com.popularmoviesapp.AsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import main.android.com.popularmoviesapp.MovieDetailsActivity;
import main.android.com.popularmoviesapp.Adapters.PopularMoviesAdapter;
import main.android.com.popularmoviesapp.R;
import main.android.com.popularmoviesapp.parcels.Movie;
import main.android.com.popularmoviesapp.utilities.NetworkUtils;

public class FetchAllMoviesAsyncTask extends AsyncTask<URL, Void, JSONArray> implements PopularMoviesAdapter.OnRecyclerViewClickListener {

    public int len;
    public PopularMoviesAdapter mAdapter;
    public ArrayList<Movie> moviePojosArrayList;
    public RecyclerView mRecyclerView;
    public Context commonContext;

    public FetchAllMoviesAsyncTask(Context context, ArrayList<Movie> moviePojosArrayList, RecyclerView mRecyclerView) {
        this.commonContext = context;
        this.moviePojosArrayList = moviePojosArrayList;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(URL... urls) {
        URL url = urls[0];
        JSONArray allMoviesJsonArray = new JSONArray();
        try {
            JSONObject allMoviesJsonObject = NetworkUtils.getResponseFromHttpUrl(url);
            allMoviesJsonArray = allMoviesJsonObject.getJSONArray(commonContext.getString(R.string.results));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allMoviesJsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray allMovies) {
        for (int i = 0; i < allMovies.length(); i++) {
            JSONObject singleMovieJsonObject = null;
            try {
                singleMovieJsonObject = allMovies.getJSONObject(i);
                String movieTitle = singleMovieJsonObject.getString(commonContext.getString(R.string.title));
                String movieReleaseDate = singleMovieJsonObject.getString(commonContext.getString(R.string.release_date));
                String movieOverview = singleMovieJsonObject.getString(commonContext.getString(R.string.overview));
                String posterPath = singleMovieJsonObject.getString(commonContext.getString(R.string.poster_path)).split(commonContext.getString(R.string.forward_slash))[1];
                String fullPosterPath = NetworkUtils.buildPosterPathUrl(posterPath).toString();
                String movieVoteAverage = singleMovieJsonObject.getString(commonContext.getString(R.string.vote_average));
                String id = singleMovieJsonObject.getString(commonContext.getString(R.string.movieId));
                Movie aMovie = new Movie(id, movieTitle, movieReleaseDate, movieOverview, fullPosterPath, movieVoteAverage);
                moviePojosArrayList.add(aMovie);
                moviePojosArrayList.add(aMovie);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//end loop
        if (mAdapter == null && moviePojosArrayList.size() != 0) {
            //Instantiating our adapter class
            mAdapter = new PopularMoviesAdapter(moviePojosArrayList, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateMoviesListWithinAdapter(moviePojosArrayList);
        }
        super.onPostExecute(allMovies);
    }

    @Override
    public void onclickListener(int itemClicked) {
        Intent movieDetailsIntent = new Intent(commonContext, MovieDetailsActivity.class);
        Movie movieClickedOn = (Movie) moviePojosArrayList.get(itemClicked);
        movieDetailsIntent.putExtra(commonContext.getString(R.string.movieId), movieClickedOn.getMovieId());
        movieDetailsIntent.putExtra(commonContext.getString(R.string.movieTitle), movieClickedOn.getMovieTitle());
        movieDetailsIntent.putExtra(commonContext.getString(R.string.movieReleaseDate), movieClickedOn.getMovieReleaseDate());
        movieDetailsIntent.putExtra(commonContext.getString(R.string.movieOverview), movieClickedOn.getMovieOverview());
        movieDetailsIntent.putExtra(commonContext.getString(R.string.movieFullPosterPath), movieClickedOn.getMovieFullPosterPath());
        movieDetailsIntent.putExtra(commonContext.getString(R.string.movieVoteAverage), movieClickedOn.getMovieVoteAverage());
        commonContext.startActivity(movieDetailsIntent);
    }


}







