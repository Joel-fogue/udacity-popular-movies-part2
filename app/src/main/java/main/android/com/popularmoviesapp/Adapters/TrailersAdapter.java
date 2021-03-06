package main.android.com.popularmoviesapp.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import main.android.com.popularmoviesapp.R;
import main.android.com.popularmoviesapp.parcels.Trailer;
import main.android.com.popularmoviesapp.utilities.NetworkUtils;

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
    public void onBindViewHolder(@NonNull SingleReviewViewHolder singleReviewViewHolder, final int i) {
        singleReviewViewHolder.bind(i);
        singleReviewViewHolder.mTrailerImageViewOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build the implicit intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(NetworkUtils.getTrailerUri(allTrailersPojosArrayList.get(i).getTrailerKey()));
                if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    v.getContext().startActivity(intent);
                }
            }
        });
        singleReviewViewHolder.mTrailerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build the implicit intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(NetworkUtils.getTrailerUri(allTrailersPojosArrayList.get(i).getTrailerKey()));
                if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    class SingleReviewViewHolder extends RecyclerView.ViewHolder {

        public ImageView mTrailerImageView, mTrailerImageViewOverlay;
        public TextView mTrailerName, mTrailerType;

        public SingleReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            mTrailerImageView = itemView.findViewById(R.id.mTrailerImageView);
            mTrailerName = itemView.findViewById(R.id.mTrailerName);
            mTrailerType = itemView.findViewById(R.id.mTrailerType);
            mTrailerImageViewOverlay = itemView.findViewById(R.id.mTrailerImageViewOverlay);
        }

        public void bind(int position) {
            String trailerKey = allTrailersPojosArrayList.get(position).getTrailerKey();
            String trailerName = allTrailersPojosArrayList.get(position).getTrailerName();
            String trailerType = allTrailersPojosArrayList.get(position).getTrailerType();
            String fullMoviePosterPath = allTrailersPojosArrayList.get(position).getFullMoviePosterPath();
            mTrailerName.setText(trailerName);
            mTrailerType.setText(trailerType);
            Picasso.get()
                    .load(fullMoviePosterPath)
                    .into(mTrailerImageView);
            //set default video player icon
            Picasso.get()
                    .load(R.drawable.video_play_icon).into(mTrailerImageViewOverlay);
        }
    }
}
