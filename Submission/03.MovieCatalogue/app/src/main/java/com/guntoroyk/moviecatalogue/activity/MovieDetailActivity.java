package com.guntoroyk.moviecatalogue.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.guntoroyk.moviecatalogue.R;
import com.guntoroyk.moviecatalogue.model.MovieItems;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra movie";

    TextView tvName;
    TextView tvOverview;
    TextView tvDate;
    ImageView imgPhoto;

    ImageButton btnBack;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvName = findViewById(R.id.tv_name);
        tvOverview = findViewById(R.id.tv_overview);
        tvDate = findViewById(R.id.tv_date);
        imgPhoto = findViewById(R.id.img_photo);

        btnBack = findViewById(R.id.btn_back);

        progressBar = findViewById(R.id.progressBar_detailMovie);
        progressBar.bringToFront();

        final MovieItems movieItems = getIntent().getParcelableExtra(EXTRA_MOVIE);

        tvName.setText(movieItems.getTitle());
        tvOverview.setText(movieItems.getOverview());
        tvDate.setText(movieItems.getDate_released());

        String url = "https://image.tmdb.org/t/p/original" + movieItems.getPoster();
        Glide.with(MovieDetailActivity.this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imgPhoto);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
