package com.guntoroyk.moviecataloge.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guntoroyk.moviecataloge.R;
import com.guntoroyk.moviecataloge.activity.TvShowDetailActivity;
import com.guntoroyk.moviecataloge.adapter.TvShowAdapter;
import com.guntoroyk.moviecataloge.data.TvShowData;
import com.guntoroyk.moviecataloge.model.TvShow;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment {
    private RecyclerView rvTvShows;
    private ArrayList<TvShow> list = new ArrayList<>();

    public TvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        rvTvShows = (RecyclerView) view.findViewById(R.id.rv_tv_show);
        rvTvShows.setHasFixedSize(true);

        // add divider
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        rvTvShows.addItemDecoration(itemDecoration);

        list.addAll(TvShowData.getListData(getContext()));
        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        rvTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
        TvShowAdapter listTvShowAdapter = new TvShowAdapter(list);
        rvTvShows.setAdapter(listTvShowAdapter);

        listTvShowAdapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow data) {
                showSelectedTvShow(data);
            }
        });
    }

    private void showSelectedTvShow(TvShow tvShow) {
        Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, tvShow);
        startActivity(intent);
    }

}
