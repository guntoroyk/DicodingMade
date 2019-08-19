package com.guntoroyk.moviecatalogue.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.guntoroyk.moviecatalogue.R;
import com.guntoroyk.moviecatalogue.activity.MovieDetailActivity;
import com.guntoroyk.moviecatalogue.activity.TvShowDetailActivity;
import com.guntoroyk.moviecatalogue.adapter.MovieAdapter;
import com.guntoroyk.moviecatalogue.adapter.TvShowAdapter;
import com.guntoroyk.moviecatalogue.model.MovieItems;
import com.guntoroyk.moviecatalogue.model.TvShowItems;
import com.guntoroyk.moviecatalogue.viewmodel.MainViewModelMovie;
import com.guntoroyk.moviecatalogue.viewmodel.MainViewModelTvShow;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowsFragment extends Fragment {

    RecyclerView rvTvShow;
    private TvShowAdapter mAdapter;
    private ProgressBar progressBar;
    private MainViewModelTvShow mainViewModelTvShow;

    public TVShowsFragment() {
        // Required empty public constructor
    }

    private android.arch.lifecycle.Observer<ArrayList<TvShowItems>> getTvShows = new Observer<ArrayList<TvShowItems>>() {
        @Override
        public void onChanged(ArrayList<TvShowItems> tvShowItems) {
            if (tvShowItems != null) {
                mAdapter.setData(tvShowItems);
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new TvShowAdapter();
        mAdapter.notifyDataSetChanged();

        rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecorator = new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
        rvTvShow.addItemDecoration(itemDecorator);
        rvTvShow.setHasFixedSize(true);

        rvTvShow.setAdapter(mAdapter);

        progressBar = view.findViewById(R.id.progressBarFragmentTvShow);
        progressBar.bringToFront();

        mAdapter.setOnItemClickListener(new TvShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TvShowItems tvShowItems) {
                Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
                intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, tvShowItems);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // MainViewModel Instance
        mainViewModelTvShow = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModelTvShow.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Observer
        mainViewModelTvShow.getTvShows().observe(Objects.requireNonNull(getActivity()), getTvShows);

        // Display The Items
        mainViewModelTvShow.setTvShow();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unsubscribing The Observer
        mainViewModelTvShow.getTvShows().removeObserver(getTvShows);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

}
