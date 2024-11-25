package com.example.appcinema;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MovieRecycler extends RecyclerView.Adapter<MovieRecycler.MovieViewHolder> {
    private List<Movie> originalList;
    private List<Movie> filteredList;

    public MovieRecycler(List<Movie> originalList) {
        this.originalList = new ArrayList<>(originalList);
        this.filteredList = new ArrayList<>(originalList);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = filteredList.get(position);
        holder.title.setText(movie.getTitle());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 1200);
        holder.imageView.setLayoutParams(layoutParams);
        holder.imageView.setImageResource(movie.getImageResource());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), movieDetails.class);
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("imageResource", movie.getImageResource());
            intent.putExtra("duration", movie.getDuration());
            intent.putExtra("genre", movie.getGenre());
            intent.putExtra("actors", movie.getActors());
            intent.putExtra("description", movie.getDescription());
            v.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (Movie movie : originalList) {
                if (movie.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(movie);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.movie_poster);
        }
    }
}

