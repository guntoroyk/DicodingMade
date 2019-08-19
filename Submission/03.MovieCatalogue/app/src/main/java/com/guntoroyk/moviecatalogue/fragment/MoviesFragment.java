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
import com.guntoroyk.moviecatalogue.adapter.MovieAdapter;
import com.guntoroyk.moviecatalogue.model.MovieItems;
import com.guntoroyk.moviecatalogue.viewmodel.MainViewModelMovie;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private MainViewModelMovie mainViewModelMovie;

    public MoviesFragment() {
        // Required empty public constructor
    }


    // Observer
    private android.arch.lifecycle.Observer<ArrayList<MovieItems>> getMovies = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                movieAdapter.setData(movieItems);
                progressBar.setVisibility(View.GONE);
            }
        }
    };


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();

        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecorator = new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
        rvMovie.addItemDecoration(itemDecorator);
        rvMovie.setHasFixedSize(true);

        rvMovie.setAdapter(movieAdapter);

        progressBar = view.findViewById(R.id.progressBarFragmentMovie);
        progressBar.bringToFront();

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieItems movieItems) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movieItems);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // MainViewModel Instance
        mainViewModelMovie = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModelMovie.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Observer
        mainViewModelMovie.getMovies().observe(Objects.requireNonNull(getActivity()), getMovies);

        // Display The Items
        mainViewModelMovie.setMovie();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unsubscribing The Observer
        mainViewModelMovie.getMovies().removeObserver(getMovies);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

}
