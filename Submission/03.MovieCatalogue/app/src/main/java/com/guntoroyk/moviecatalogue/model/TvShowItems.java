package com.guntoroyk.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShowItems implements Parcelable {
    private String original_name;
    private String first_air_date;
    private String overview;
    private String poster;


    public TvShowItems(JSONObject object) {
        try {
            String original_name = object.getString("original_name");
            String first_air_date = object.getString("first_air_date");
            String overview = object.getString("overview");
            String poster = object.getString("poster_path");
            this.original_name = original_name;
            this.first_air_date = first_air_date;
            this.overview = overview;
            this.poster = poster;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original_name);
        dest.writeString(this.first_air_date);
        dest.writeString(this.overview);
        dest.writeString(this.poster);
    }

    protected TvShowItems(Parcel in) {
        this.original_name = in.readString();
        this.first_air_date = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
    }

    public static final Creator<TvShowItems> CREATOR = new Creator<TvShowItems>() {
        @Override
        public TvShowItems createFromParcel(Parcel source) {
            return new TvShowItems(source);
        }

        @Override
        public TvShowItems[] newArray(int size) {
            return new TvShowItems[size];
        }
    };
}
