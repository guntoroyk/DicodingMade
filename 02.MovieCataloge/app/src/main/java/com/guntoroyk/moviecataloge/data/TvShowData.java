package com.guntoroyk.moviecataloge.data;

import android.content.Context;
import android.content.res.TypedArray;

import com.guntoroyk.moviecataloge.R;
import com.guntoroyk.moviecataloge.model.TvShow;

import java.util.ArrayList;

public class TvShowData {
    public static ArrayList<TvShow> getListData(Context context) {
        String[] dataName;
        String[] dataDate;
        String[] dataOverview;
        TypedArray dataPhoto;

        dataName = context.getResources().getStringArray(R.array.data_name_tv_show);
        dataDate = context.getResources().getStringArray(R.array.data_date_tv_show);
        dataOverview = context.getResources().getStringArray(R.array.data_overview_tv_show);
        dataPhoto = context.getResources().obtainTypedArray(R.array.data_photo_tv_show);


        ArrayList<TvShow> list = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setPhoto(dataPhoto.getResourceId(i, -1));
            tvShow.setName(dataName[i]);
            tvShow.setDate(dataDate[i]);
            tvShow.setOverview(dataOverview[i]);
            list.add(tvShow);
        }
        return list;
    }
}
