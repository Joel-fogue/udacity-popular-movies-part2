package main.android.com.popularmoviesapp.parcels;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer implements Parcelable{

    public String id, trailerKey, trailerName, trailerType;

    public Trailer(String id, String trailerKey, String trailerName, String trailerType) {
        this.id = id;
        this.trailerKey = trailerKey;
        this.trailerName = trailerName;
        this.trailerType = trailerType;
    }

    public Trailer(Parcel parcel){
        this.id = parcel.readString();
        this.trailerKey = parcel.readString();
        this.trailerName = parcel.readString();
        this.trailerType = parcel.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public void setTrailerName(String trailerName) {
        this.trailerName = trailerName;
    }

    public String getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(trailerKey);
        parcel.writeString(trailerName);
        parcel.writeString(trailerType);
    }

    Creator<Trailer> CREATOR = new Creator<Trailer>(){

        @Override
        public Trailer createFromParcel(Parcel parcel) {
            return new Trailer(parcel);
        }

        @Override
        public Trailer[] newArray(int i) {
            return new Trailer[i];
        }
    };
}
