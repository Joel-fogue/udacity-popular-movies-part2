package main.android.com.popularmoviesapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import main.android.com.popularmoviesapp.R;
import main.android.com.popularmoviesapp.parcels.Trailer;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.SingleReviewViewHolder> {
    ArrayList<Trailer> allTrailersPojosArrayList;

    public TrailersAdapter(ArrayList allTrailersPojosArrayList) {
        this.allTrailersPojosArrayList = allTrailersPojosArrayList;
    }

    @Override
    public SingleReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View viewHolder = layoutInflater.inflate(R.layout.single_trailer, viewGroup, false);
        SingleReviewViewHolder singleReviewViewHolder = new SingleReviewViewHolder(viewHolder);
        return singleReviewViewHolder;
    }

    @Override
    public int getItemCount() {
        return allTrailersPojosArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SingleReviewViewHolder singleReviewViewHolder, int i) {
        singleReviewViewHolder.bind(i);
    }

    class SingleReviewViewHolder extends RecyclerView.ViewHolder {

        public ImageView mTrailerImageView;
        public TextView mTrailerName, mTrailerType;

        public SingleReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            mTrailerImageView = itemView.findViewById(R.id.mTrailerImageView);
            mTrailerName = itemView.findViewById(R.id.mTrailerName);
            mTrailerType = itemView.findViewById(R.id.mTrailerType);
        }

        public void bind(int position) {
            String trailerKey = allTrailersPojosArrayList.get(position).getTrailerKey();
            String trailerName = allTrailersPojosArrayList.get(position).getTrailerName();
            String trailerType = allTrailersPojosArrayList.get(position).getTrailerType();
            Log.v("trailer name is 3: ", trailerName);

            mTrailerName.setText(trailerName);
            mTrailerType.setText(trailerType);
//            String fullPosterPathUrl = allMoviePojosArrayList.get(position).getMovieFullPosterPath();
//            Picasso.get()
//                    .load(fullPosterPathUrl)
//                    .into(singleMovieImageView);
        }
    }

}
