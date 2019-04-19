package main.android.com.popularmoviesapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import main.android.com.popularmoviesapp.R;
import main.android.com.popularmoviesapp.parcels.Review;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.SingleReviewViewHolder> {
    ArrayList<Review> allTrailersPojosArrayList;

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

        public TextView mReviewAuthor, mReviewContent;

        public SingleReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            mReviewAuthor = itemView.findViewById(R.id.mReviewAuthor);
            mReviewContent = itemView.findViewById(R.id.mTrailersContent);
        }

        public void bind(int position) {
            String trailerKey = allTrailersPojosArrayList.get(position).getReviewAuthor();
            String trailerName = allTrailersPojosArrayList.get(position).getReviewContent();
            mReviewAuthor.setText(reviewAuthor);
            mReviewContent.setText(reviewContent);
        }
    }

}
