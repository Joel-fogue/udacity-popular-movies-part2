package main.android.com.popularmoviesapp.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import main.android.com.popularmoviesapp.Adapters.PopularMoviesAdapter;
import main.android.com.popularmoviesapp.Adapters.ReviewsAdapter;
import main.android.com.popularmoviesapp.R;
import main.android.com.popularmoviesapp.parcels.Movie;
import main.android.com.popularmoviesapp.parcels.Review;
import main.android.com.popularmoviesapp.utilities.NetworkUtils;

public class FetchAllReviewsAsyncTask extends AsyncTask<URL, Void, JSONArray> {
    public ArrayList<Review> mReviewsPojosArrayList;
    public ReviewsAdapter mReviewsAdapter;
    public RecyclerView mReviewsRecyclerView;
    public Context mReviewsContext;

    public FetchAllReviewsAsyncTask(Context context, ArrayList<Review> mReviewsPojosArrayList, RecyclerView mReviewsRecyclerView){
        //super(context, moviePojosArrayList, mRecyclerView);
        this.mReviewsContext=context;
        this.mReviewsPojosArrayList=mReviewsPojosArrayList;
        this.mReviewsRecyclerView=mReviewsRecyclerView;
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
            allMoviesJsonArray = allMoviesJsonObject.getJSONArray(mReviewsContext.getString(R.string.results));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allMoviesJsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray allReviews) {
        Log.v("review 2", allReviews.length()+"");
        for (int i = 0; i < allReviews.length(); i++) {
            JSONObject singleReviewJsonObject = null;
            try {
                singleReviewJsonObject = allReviews.getJSONObject(i);
                String reviewAuthor = singleReviewJsonObject.getString(mReviewsContext.getString(R.string.reviewAuthor));
                String reviewContent = singleReviewJsonObject.getString(mReviewsContext.getString(R.string.reviewContent));
                String id = singleReviewJsonObject.getString(mReviewsContext.getString(R.string.reviewId));
                Review aReview = new Review(id, reviewAuthor, reviewContent);
                mReviewsPojosArrayList.add(aReview);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//end loop
        //if (mReviewsAdapter == null && mReviewsPojosArrayList.size() != 0) {
            //Instantiating our adapter class
            mReviewsAdapter = new ReviewsAdapter(mReviewsPojosArrayList);
        Log.v("review 1", mReviewsPojosArrayList.get(0).getReviewAuthor());
            mReviewsRecyclerView.setAdapter(mReviewsAdapter);
//        } else {
//            mAdapter.updateMoviesListWithinAdapter(mReviewsPojosArrayList);
//        }
        super.onPostExecute(allReviews);
    }






}







