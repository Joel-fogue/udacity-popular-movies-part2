package main.android.com.popularmoviesapp.parcels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Movie implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private String id;
    private String movieTitle;
    private String movieReleaseDate;
    private String movieOverview;
    private String movieFullPosterPath;
    private String movieVoteAverage;

    public Movie(String id, String movieTitle, String movieReleaseDate, String movieOverview, String moviePosterPath, String movieVoteAverage) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.movieReleaseDate = movieReleaseDate;
        this.movieOverview = movieOverview;
        this.movieFullPosterPath = moviePosterPath;
        this.movieVoteAverage = movieVoteAverage;
    }
    
    //We need this second constructor WITHOUT the id since a movie will get added to the DB with a AUTO-INCREMENTED ID
    public Movie(String movieTitle, String movieReleaseDate, String movieOverview, String moviePosterPath, String movieVoteAverage) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.movieReleaseDate = movieReleaseDate;
        this.movieOverview = movieOverview;
        this.movieFullPosterPath = moviePosterPath;
        this.movieVoteAverage = movieVoteAverage;
    }


    public Movie(Parcel parcel){
        this.id = parcel.readString();
        this.movieTitle = parcel.readString();
        this.movieReleaseDate = parcel.readString();
        this.movieOverview = parcel.readString();
        this.movieFullPosterPath = parcel.readString();
        this.movieVoteAverage = parcel.readString();
    }

    public String getMovieId() {
        return id;
    }

    public void setMovieId(String id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieFullPosterPath() {
        return movieFullPosterPath;
    }

    public void setMovieFullPosterPath(String movieFullPosterPath) {
        this.movieFullPosterPath = movieFullPosterPath;
    }

    public String getMovieVoteAverage() {
        return movieVoteAverage;
    }

    public void setMovieVoteAverage(String movieVoteAverage) {
        this.movieVoteAverage = movieVoteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(movieTitle);
        parcel.writeString(movieReleaseDate);
        parcel.writeString(movieOverview);
        parcel.writeString(movieFullPosterPath);
        parcel.writeString(movieVoteAverage);
    }

    Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){

        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
}
