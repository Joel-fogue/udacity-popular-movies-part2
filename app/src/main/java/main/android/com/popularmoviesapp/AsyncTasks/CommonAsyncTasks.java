//package main.android.com.popularmoviesapp.AsyncTasks;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.support.v7.widget.RecyclerView;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.net.URL;
//import java.util.ArrayList;
//
//import main.android.com.popularmoviesapp.Adapters.PopularMoviesAdapter;
//import main.android.com.popularmoviesapp.R;
//import main.android.com.popularmoviesapp.parcels.Movie;
//import main.android.com.popularmoviesapp.utilities.NetworkUtils;
//
//public class CommonAsyncTasks extends AsyncTask<URL, Void, JSONArray> {
//
//    public int len;
//    public PopularMoviesAdapter mAdapter;
//    public ArrayList<?> moviePojosArrayList;
//    public RecyclerView mRecyclerView;
//    public Context commonContext;
//
//    public CommonAsyncTasks(Context context, ArrayList<Movie> moviePojosArrayList, RecyclerView mRecyclerView){
//        this.commonContext = context;
//        this.moviePojosArrayList = moviePojosArrayList;
//        this.mRecyclerView = mRecyclerView;
//    }
//    //default constructor
//    public CommonAsyncTasks(){}
//
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected JSONArray doInBackground(URL... urls) {
//        URL url = urls[0];
//        JSONArray allMoviesJsonArray = new JSONArray();
//        try {
//            JSONObject allMoviesJsonObject = NetworkUtils.getResponseFromHttpUrl(url);
//            allMoviesJsonArray = allMoviesJsonObject.getJSONArray(commonContext.getString(R.string.results));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return allMoviesJsonArray;
//    }
//
//}