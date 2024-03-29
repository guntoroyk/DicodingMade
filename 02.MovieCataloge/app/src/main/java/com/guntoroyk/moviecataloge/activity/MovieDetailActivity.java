package com.guntoroyk.moviecataloge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.guntoroyk.moviecataloge.R;
import com.guntoroyk.moviecataloge.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvDate;
    TextView lbOverview;
    TextView tvOverview;
    ImageView ivPhoto;

    private String txtName;
    private String txtDate;
    private String txtOverview;
    private int intPhoto;

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvName = findViewById(R.id.tv_name);
        tvDate = findViewById(R.id.tv_date);
        tvOverview = findViewById(R.id.tv_overview);
        lbOverview = findViewById(R.id.tv_label_overview);
        ivPhoto = findViewById(R.id.img_photo);

        getSupportActionBar().setTitle(getResources().getString(R.string.detail_movie));

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        txtName = movie.getName();
        txtDate = movie.getDate();
        txtOverview = movie.getOverview();
        intPhoto = movie.getPhoto();

        tvName.setText(txtName);
        tvDate.setText(txtDate);
        lbOverview.setText(getString(R.string.overview));
        tvOverview.setText(txtOverview);
        ivPhoto.setImageResource(intPhoto);
    }
}
