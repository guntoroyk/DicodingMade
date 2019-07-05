package com.guntoroyk.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataName;
    private String[] dataDate;
    private  String[] dataOverview;
    private TypedArray dataPhoto;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Movie Catalogue");

        adapter = new MovieAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent moveToDetailMovieIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
                moveToDetailMovieIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movies.get(i));
                startActivity(moveToDetailMovieIntent);
            }
        });
    }

    private void addItem() {
        movies = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setName(dataName[i]);
            movie.setDate(dataDate[i]);
            movie.setOverview(dataOverview[i]);
            movies.add(movie);
        }
        adapter.setMovies(movies);
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name);
        dataDate = getResources().getStringArray(R.array.data_date);
        dataOverview = getResources().getStringArray(R.array.data_overview);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }
}
