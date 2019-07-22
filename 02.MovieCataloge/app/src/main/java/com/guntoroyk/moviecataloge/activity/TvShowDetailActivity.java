package com.guntoroyk.moviecataloge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.guntoroyk.moviecataloge.R;
import com.guntoroyk.moviecataloge.model.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvDate;
    TextView lbOverview;
    TextView tvOverview;
    ImageView ivPhoto;

    private String txtName;
    private String txtDate;
    private String txtOverview;
    private int intPhoto;

    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        tvName = findViewById(R.id.tv_name);
        tvDate = findViewById(R.id.tv_date);
        tvOverview = findViewById(R.id.tv_overview);
        lbOverview = findViewById(R.id.tv_label_overview);
        ivPhoto = findViewById(R.id.img_photo);

        getSupportActionBar().setTitle(R.string.detail_tv_show);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        txtName = tvShow.getName();
        txtDate = tvShow.getDate();
        txtOverview = tvShow.getOverview();
        intPhoto = tvShow.getPhoto();

        tvName.setText(txtName);
        tvDate.setText(txtDate);
        lbOverview.setText(getString(R.string.overview));
        tvOverview.setText(txtOverview);
        ivPhoto.setImageResource(intPhoto);
    }
}
