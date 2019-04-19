package main.android.com.popularmoviesapp.parcels;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer implements Parcelable{

    private String id, trailerKey, trailerName;

    public Trailer(String id, String trailerKey, String trailerName) {
        this.id = id;
        this.trailerKey = trailerKey;
        this.trailerName = trailerName;
    }

    public Trailer(Parcel parcel){
        this.id = parcel.readString();
        this.trailerKey = parcel.readString();
        this.trailerName = parcel.readString();
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
