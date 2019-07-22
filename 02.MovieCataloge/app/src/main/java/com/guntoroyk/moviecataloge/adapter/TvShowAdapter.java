package com.guntoroyk.moviecataloge.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guntoroyk.moviecataloge.R;
import com.guntoroyk.moviecataloge.model.TvShow;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ListViewHolder> {

    private ArrayList<TvShow> listTvShow;

    public TvShowAdapter(ArrayList<TvShow> list) {
        this.listTvShow = list;
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show, viewGroup, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        TvShow tvShow = listTvShow.get(position);

        Glide.with(holder.itemView.getContext())
                .load(tvShow.getPhoto())
                .into(holder.imgPhoto);

        holder.tvName.setText(tvShow.getName());
        holder.tvOverview.setText(tvShow.getOverview());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked((listTvShow.get(holder.getAdapterPosition())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvOverview;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvOverview = itemView.findViewById(R.id.tv_overview);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(TvShow data);
    }
}
