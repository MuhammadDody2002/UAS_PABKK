package com.if5b.komik_kamikaze;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {
    private String id_komik;
    private String nm_komik;
    private String gbr_komik;
    private String genre;

    protected Result(Parcel in) {
        id_komik = in.readString();
        nm_komik = in.readString();
        gbr_komik = in.readString();
        genre = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_komik);
        dest.writeString(nm_komik);
        dest.writeString(gbr_komik);
        dest.writeString(genre);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getId_komik() {
        return id_komik;
    }

    public String getNm_komik() {
        return nm_komik;
    }

    public String getGbr_komik() {
        return gbr_komik;
    }

    public String getGenre() {
        return genre;
    }
}
