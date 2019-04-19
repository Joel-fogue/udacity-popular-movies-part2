package main.android.com.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import main.android.com.popularmoviesapp.AsyncTasks.FetchAllReviewsAsyncTask;
import main.android.com.popularmoviesapp.parcels.Review;
import main.android.com.popularmoviesapp.utilities.NetworkUtils;

public class MovieDetailsActivity extends AppCompatActivity {


    public TextView mTitle, mReleaseDate, mOverview, mFavorite, mMovieVoteAverage;
    public ImageView mMoviePic;
    //recyclerview for reviews
    private RecyclerView mReviewsRecyclerView;
    private RecyclerView.Adapter mReviewsAdapter;
    private RecyclerView.LayoutManager mReviewslayoutManager;
    private ArrayList<Review> mReviewsArrayList;
    public String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        mMoviePic = findViewById(R.id.movieDetailPic);
        mTitle = findViewById(R.id.title);
        mReleaseDate = findViewById(R.id.year);
        mOverview = findViewById(R.id.overview);
        mFavorite = findViewById(R.id.favorite);
        mMovieVoteAverage = findViewById(R.id.vote_average);

        Intent intentThatCreatedThisActivity = getIntent();
        if (intentThatCreatedThisActivity != null &&
                intentThatCreatedThisActivity.hasExtra(getString(R.string.movieTitle)) &&
                intentThatCreatedThisActivity.hasExtra(getString(R.string.movieReleaseDate)) &&
                intentThatCreatedThisActivity.hasExtra(getString(R.string.movieOverview)) &&
                intentThatCreatedThisActivity.hasExtra(getString(R.string.movieFullPosterPath)) &&
                intentThatCreatedThisActivity.hasExtra(getString(R.string.movieVoteAverage))) {
            String mMovieTitle = intentThatCreatedThisActivity.getStringExtra(getString(R.string.movieTitle));
            String mMovieReleaseDate = intentThatCreatedThisActivity.getStringExtra(getString(R.string.movieReleaseDate));
            String mMovieOverview = intentThatCreatedThisActivity.getStringExtra(getString(R.string.movieOverview));
            String mMovieFullPosterPath = intentThatCreatedThisActivity.getStringExtra(getString(R.string.movieFullPosterPath));
            String movieVoteAverage = intentThatCreatedThisActivity.getStringExtra(getString(R.string.movieVoteAverage));
            movieId = intentThatCreatedThisActivity.getStringExtra(getString(R.string.movieId));
            mTitle.setText(mMovieTitle);
            mReleaseDate.setText(mMovieReleaseDate);
            mOverview.setText(mMovieOverview);
            mMovieVoteAverage.setText(movieVoteAverage + getString(R.string.forwardSlashTen));
            Picasso.get()
                    .load(mMovieFullPosterPath)
                    .fit()
                    .centerCrop()
                    .into(mMoviePic);
        }//end if
        mReviewsArrayList = new ArrayList<Review>();
        //recyclerView for reviews
        mReviewsRecyclerView = (RecyclerView) findViewById(R.id.reviewsId);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mReviewsRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mReviewslayoutManager = new LinearLayoutManager(this);
        mReviewsRecyclerView.setLayoutManager(mReviewslayoutManager);

        //Adapter gets set in the FetchAllReviewsAsyncTask class

        //Get the reviews api calls
        ///movie/{id}/reviews
        URL moviesUrl = NetworkUtils.buildOtherUrls(movieId + "/" + getString(R.string.review_endpoint));
        if (isOnline())
            fetchAllReviews(moviesUrl);

        //Get the trailers
        ///movie/{id}/videos


    }//end onCreate()

    public void fetchAllReviews(URL url) {
        new FetchAllReviewsAsyncTask(MovieDetailsActivity.this, mReviewsArrayList, mReviewsRecyclerView).execute(url);
    }

    /**
     * SADLY I'M BREAKING THE DRY PRINCIPLE HERE BY REPEATING THIS CODE; DIDN'T WANT TO FIGHT WITH THE CONTEXT
     * Got this code from here: https://developer.android.com/training/monitoring-device-state/connectivity-monitoring
     *
     * @return a boolean true if we're connected
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnected();
    }

}
