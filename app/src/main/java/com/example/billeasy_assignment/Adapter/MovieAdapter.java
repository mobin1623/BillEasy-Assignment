package com.example.billeasy_assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.billeasy_assignment.DB.Movies;
import com.example.billeasy_assignment.R;
import com.example.billeasy_assignment.Utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movies> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public MovieAdapter(List<Movies> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movie_list_item,parent,false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MoviesHolder) {
            MoviesHolder moviesHolder = (MoviesHolder) holder;
            Glide.with(context).load(Constants.POSTER_PATH +
                    list.get(position).getPosterPath()).into(moviesHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MoviesHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MoviesHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
