package com.guntoroyk.moviecataloge.fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guntoroyk.moviecataloge.R;
import com.guntoroyk.moviecataloge.adapter.MovieAdapter;
import com.guntoroyk.moviecataloge.data.MovieData;
import com.guntoroyk.moviecataloge.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        rvMovies = (RecyclerView) view.findViewById(R.id.rv_movie);
        rvMovies.setHasFixedSize(true);

        list.addAll(MovieData.getListData(getContext()));

        showRecyclerList();
        return view;
    }

    private void showRecyclerList(){
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter listHeroAdapter = new MovieAdapter(list);
        rvMovies.setAdapter(listHeroAdapter);
    }
}
