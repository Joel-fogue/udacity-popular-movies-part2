package main.android.com.popularmoviesapp.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "movie") //just making the table lowercase
public class Movie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "movie_id")
    private String movieId;
    @ColumnInfo(name = "movie_title")
    private String movieTitle;
    @ColumnInfo(name = "movie_release_date")
    private String movieReleaseDate;
    @ColumnInfo(name = "movie_overview")
    private String movieOverview;
    @ColumnInfo(name = "movie_full_poster_path")
    private String movieFullPosterPath;
    @ColumnInfo(name = "movie_vote_average")
    private String movieVoteAverage;

    //Pojo constructor without ROOM stuff
    //The Ignore annotation let's ROOM know that it needs to use the second constructor which has the ID
    @Ignore
    public Movie(String movieId, String movieTitle, String movieReleaseDate, String movieOverview, String moviePosterPath, String movieVoteAverage) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieReleaseDate = movieReleaseDate;
        this.movieOverview = movieOverview;
        this.movieFullPosterPath = moviePosterPath;
        this.movieVoteAverage = movieVoteAverage;
    }

    //Second constructor for Room with id -> AUTO-INCREMENT
    public Movie(int id, String movieId, String movieTitle, String movieReleaseDate, String movieOverview, String moviePosterPath, String movieVoteAverage) {
        this.id = id; //AUTO-INCREMENT
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieReleaseDate = movieReleaseDate;
        this.movieOverview = movieOverview;
        this.movieFullPosterPath = moviePosterPath;
        this.movieVoteAverage = movieVoteAverage;
    }

    @Ignore
    public Movie(Parcel parcel) {
        this.movieId = parcel.readString();
        this.movieTitle = parcel.readString();
        this.movieReleaseDate = parcel.readString();
        this.movieOverview = parcel.readString();
        this.movieFullPosterPath = parcel.readString();
        this.movieVoteAverage = parcel.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String id) {
        this.movieId = id;
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
        parcel.writeString(movieId);
        parcel.writeString(movieTitle);
        parcel.writeString(movieReleaseDate);
        parcel.writeString(movieOverview);
        parcel.writeString(movieFullPosterPath);
        parcel.writeString(movieVoteAverage);
    }

    @Ignore()
    Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

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
