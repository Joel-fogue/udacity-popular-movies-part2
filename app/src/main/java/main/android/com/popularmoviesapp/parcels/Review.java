package main.android.com.popularmoviesapp.parcels;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable{

    private String id, reviewAuthor, reviewContent;

    public Review(String id, String reviewAuthor, String reviewContent) {
        this.id = id;
        this.reviewAuthor = reviewAuthor;
        this.reviewContent = reviewContent;
    }

    public Review(Parcel parcel){
        this.id = parcel.readString();
        this.reviewAuthor = parcel.readString();
        this.reviewContent = parcel.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(reviewAuthor);
        parcel.writeString(reviewContent);
    }

    Creator<Review> CREATOR = new Creator<Review>(){

        @Override
        public Review createFromParcel(Parcel parcel) {
            return new Review(parcel);
        }

        @Override
        public Review[] newArray(int i) {
            return new Review[i];
        }
    };
}
