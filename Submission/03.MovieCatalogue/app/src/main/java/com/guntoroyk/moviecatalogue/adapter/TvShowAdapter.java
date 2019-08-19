package com.guntoroyk.moviecatalogue.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.guntoroyk.moviecatalogue.R;
import com.guntoroyk.moviecatalogue.model.TvShowItems;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private ArrayList<TvShowItems> tvShowsData = new ArrayList<>();
    private OnItemClickListener listener;

    public void setData(ArrayList<TvShowItems> items) {
        tvShowsData.clear();
        tvShowsData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show, viewGroup, false);
        return new TvShowViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvShowViewHolder TvShowViewHolder, int position) {
        TvShowViewHolder.tvName.setText(tvShowsData.get(position).getOriginal_name());
        TvShowViewHolder.tvOverview.setText(tvShowsData.get(position).getOverview());
        String uri = "https://image.tmdb.org/t/p/original" + tvShowsData.get(position).getPoster();
        Glide.with(TvShowViewHolder.itemView.getContext())
                .load(uri)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        TvShowViewHolder.progressBarItemTvShow.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(TvShowViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return tvShowsData.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgPhoto;
        TextView tvFirstAirDate;
        TextView tvOverview;
        ProgressBar progressBarItemTvShow;

        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvFirstAirDate = itemView.findViewById(R.id.tv_date);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            progressBarItemTvShow = itemView.findViewById(R.id.progressBar_itemTvShow);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tvShowsData.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TvShowItems TvShowItems);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

