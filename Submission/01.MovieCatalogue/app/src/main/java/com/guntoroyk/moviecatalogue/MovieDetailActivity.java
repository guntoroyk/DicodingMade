package com.guntoroyk.moviecatalogue;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvDate;
    TextView lbOverview;
    TextView tvOverview;
    ImageView ivPhoto;

    public String txtName;
    public String txtDate;
    public String txtOverview;
    public int intPhoto;

    public static final String EXTRA_MOVIE = "extra_movie";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvName = findViewById(R.id.tv_name);
        tvDate = findViewById(R.id.tv_date);
        lbOverview = findViewById(R.id.text_label_overview);
        tvOverview = findViewById(R.id.tv_overview);
        ivPhoto = findViewById(R.id.img_photo);

        getSupportActionBar().setTitle("Detail Movie");

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        txtName = movie.getName();
        txtDate = movie.getDate();
        txtOverview = movie.getOverview();
        intPhoto = movie.getPhoto();

        tvName.setText(txtName);
        tvDate.setText(txtDate);
        lbOverview.setText("Overview");
        tvOverview.setText(txtOverview);
        ivPhoto.setImageResource(intPhoto);
    }
}
