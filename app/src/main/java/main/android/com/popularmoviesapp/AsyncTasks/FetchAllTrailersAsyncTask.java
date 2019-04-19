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
import main.android.com.popularmoviesapp.Adapters.TrailersAdapter;
import main.android.com.popularmoviesapp.R;
import main.android.com.popularmoviesapp.parcels.Movie;
import main.android.com.popularmoviesapp.parcels.Review;
import main.android.com.popularmoviesapp.parcels.Trailer;
import main.android.com.popularmoviesapp.utilities.NetworkUtils;

public class FetchAllTrailersAsyncTask extends AsyncTask<URL, Void, JSONArray> {
    public ArrayList<Trailer> mTrailersPojosArrayList;
    public TrailersAdapter mTrailersAdapter;
    public RecyclerView mTrailersRecyclerView;
    public Context mTrailersContext;

    public FetchAllTrailersAsyncTask(Context context, ArrayList<Trailer> mTrailersPojosArrayList, RecyclerView mTrailersRecyclerView){
        //super(context, moviePojosArrayList, mRecyclerView);
        this.mTrailersContext=context;
        this.mTrailersPojosArrayList=mTrailersPojosArrayList;
        this.mTrailersRecyclerView=mTrailersRecyclerView;
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
            allMoviesJsonArray = allMoviesJsonObject.getJSONArray(mTrailersContext.getString(R.string.results));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allMoviesJsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray allTrailers) {
        Log.v("trailer 2", allTrailers.length()+"");
        for (int i = 0; i < allTrailers.length(); i++) {
            JSONObject singleTrailerJsonObject = null;
            try {
                singleTrailerJsonObject = allTrailers.getJSONObject(i);
                String trailerId = singleTrailerJsonObject.getString(mTrailersContext.getString(R.string.trailerId));
                String trailerName = singleTrailerJsonObject.getString(mTrailersContext.getString(R.string.trailerName));
                String trailerKey = singleTrailerJsonObject.getString(mTrailersContext.getString(R.string.trailerKey));
                Trailer aTrailer = new Trailer(trailerId, trailerName, trailerKey);
                mTrailersPojosArrayList.add(aTrailer);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//end loop
        if (mTrailersAdapter == null && mTrailersPojosArrayList.size() != 0) {
            //Instantiating our adapter class
            mTrailersAdapter = new TrailersAdapter(mTrailersPojosArrayList);
            mTrailersRecyclerView.setAdapter(mTrailersAdapter);
        }
// else {
//            mAdapter.updateMoviesListWithinAdapter(mTrailersPojosArrayList);
//        }
        super.onPostExecute(allTrailers);
    }






}







