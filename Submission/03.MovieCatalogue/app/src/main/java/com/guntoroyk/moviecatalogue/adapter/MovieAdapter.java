package com.guntoroyk.moviecatalogue.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieItems> moviesData = new ArrayList<>();
    private OnItemClickListener listener;

   public void setData(ArrayList<MovieItems> items) {
        moviesData.clear();
        moviesData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.tvName.setText(moviesData.get(position).getTitle());
        movieViewHolder.tvOverview.setText(moviesData.get(position).getOverview());
//        movieViewHolder.tvDateReleased.setText(moviesData.get(position).getDate_released());
        String uri = "https://image.tmdb.org/t/p/original" + moviesData.get(position).getPoster();
        Glide.with(movieViewHolder.itemView.getContext())
                .load(uri)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        movieViewHolder.progressBarItemMovie.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(movieViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgPhoto;
        TextView tvDateReleased;
        TextView tvOverview;
        ProgressBar progressBarItemMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvDateReleased = itemView.findViewById(R.id.tv_date);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            progressBarItemMovie = itemView.findViewById(R.id.progressBar_itemMovie);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(moviesData.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MovieItems movieItems);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
