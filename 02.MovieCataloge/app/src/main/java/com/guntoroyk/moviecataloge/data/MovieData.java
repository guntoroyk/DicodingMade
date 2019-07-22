package com.guntoroyk.moviecataloge.data;

import android.content.Context;
import android.content.res.TypedArray;

import com.guntoroyk.moviecataloge.R;
import com.guntoroyk.moviecataloge.model.Movie;

import java.util.ArrayList;

public class MovieData {

    public static ArrayList<Movie> getListData(Context context) {
        String[] dataName;
        String[] dataDate;
        String[] dataOverview;
        TypedArray dataPhoto;

        dataName = context.getResources().getStringArray(R.array.data_name);
        dataDate = context.getResources().getStringArray(R.array.data_date);
        dataOverview = context.getResources().getStringArray(R.array.data_overview);
        dataPhoto = context.getResources().obtainTypedArray(R.array.data_photo);


        ArrayList<Movie> list = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setName(dataName[i]);
            movie.setDate(dataDate[i]);
            movie.setOverview(dataOverview[i]);
            list.add(movie);
        }

        return list;
    }


}
