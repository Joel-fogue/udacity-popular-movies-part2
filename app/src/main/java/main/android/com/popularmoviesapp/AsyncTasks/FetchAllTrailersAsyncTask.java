//package main.android.com.popularmoviesapp.AsyncTasks;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import main.android.com.popularmoviesapp.Adapters.PopularMoviesAdapter;
//import main.android.com.popularmoviesapp.R;
//import main.android.com.popularmoviesapp.parcels.Movie;
//import main.android.com.popularmoviesapp.utilities.NetworkUtils;
//
//public class FetchAllTrailersAsyncTask extends CommonAsyncTasks {
//
//    public FetchAllTrailersAsyncTask(Context context, ArrayList<Movie> moviePojosArrayList, RecyclerView mRecyclerView){
//        super(context, moviePojosArrayList, mRecyclerView);
//    }
//
//    @Override
//    protected void onPostExecute(JSONArray allMovies) {
//        for (int i = 0; i < allMovies.length(); i++) {
//            JSONObject singleMovieJsonObject = null;
//            try {
//                singleMovieJsonObject = allMovies.getJSONObject(i);
//                String movieTitle = singleMovieJsonObject.getString(commonContext.getString(R.string.title));
//                String movieReleaseDate = singleMovieJsonObject.getString(commonContext.getString(R.string.release_date));
//                String movieOverview = singleMovieJsonObject.getString(commonContext.getString(R.string.overview));
//                String posterPath = singleMovieJsonObject.getString(commonContext.getString(R.string.poster_path)).split(commonContext.getString(R.string.forward_slash))[1];
//                String fullPosterPath = NetworkUtils.buildPosterPathUrl(posterPath).toString();
//                String movieVoteAverage = singleMovieJsonObject.getString(commonContext.getString(R.string.vote_average));
//                String id = singleMovieJsonObject.getString(commonContext.getString(R.string.movieId));
//                Movie aMovie = new Movie(id, movieTitle, movieReleaseDate, movieOverview, fullPosterPath, movieVoteAverage);
//                moviePojosArrayList.add(aMovie);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }//end loop
//        if (mAdapter == null && moviePojosArrayList.size() != 0) {
//            //Instantiating our adapter class
//            mAdapter = new PopularMoviesAdapter(moviePojosArrayList, this);
//            mRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.updateMoviesListWithinAdapter(moviePojosArrayList);
//        }
//        super.onPostExecute(allMovies);
//    }
//
//
//
//
//
//
//}
//
//
//
//
//
//
//
