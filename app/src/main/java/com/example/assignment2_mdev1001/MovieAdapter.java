package com.example.assignment2_mdev1001;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
//public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
//    private List<Movie> movies;
//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onEdit(Movie movie);
//        void onDelete(Movie movie);
//    }
//
//    public MovieAdapter(List<Movie> movies, OnItemClickListener listener) {
//        this.movies = movies;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
//        return new MovieViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
//        Movie movie = movies.get(position);
//        holder.title.setText(movie.getTitle());
//        holder.studio.setText(movie.getStudio());
//        holder.rating.setText(String.valueOf(movie.getRating()));
//        Log.d("CSV_LOADER","Image url: "+movie.getPosterUrl());
////        Glide.with(holder.poster.getContext()).load(movie.getPosterUrl()).into(holder.poster);
//        Glide.with(holder.poster.getContext())
//                .load(movie.getPosterUrl())
//                .placeholder(R.drawable.placeholder_image) // Default image if loading fails
//                .error(R.drawable.placeholder_image) // Error image if URL is broken
//                .into(holder.poster);
//        holder.itemView.setOnClickListener(v -> listener.onEdit(movie));
//        holder.deleteButton.setOnClickListener(v -> listener.onDelete(movie));
//    }
//
//    @Override
//    public int getItemCount() {
//        return movies.size();
//    }
//
//    public static class MovieViewHolder extends RecyclerView.ViewHolder {
//        TextView title, studio, rating;
//        ImageView poster, deleteButton;
//
//        public MovieViewHolder(View itemView) {
//            super(itemView);
//            title = itemView.findViewById(R.id.movieTitle);
//            studio = itemView.findViewById(R.id.movieStudio);
//            rating = itemView.findViewById(R.id.movieRating);
//            poster = itemView.findViewById(R.id.moviePoster);
//            deleteButton = itemView.findViewById(R.id.deleteButton);
//        }
//    }
//}




public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onEditClick(Movie movie);
        void onDeleteClick(Movie movie);
    }

    public MovieAdapter(List<Movie> movieList, OnMovieClickListener listener) {
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.studio.setText(movie.getStudio());
        holder.rating.setText(String.valueOf(movie.getRating()));
        Glide.with(holder.itemView.getContext()).load(movie.getPosterUrl()).into(holder.poster);

        holder.edit.setOnClickListener(v -> listener.onEditClick(movie));
        holder.delete.setOnClickListener(v -> listener.onDeleteClick(movie));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, studio, rating;
        ImageView poster, edit, delete;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            studio = itemView.findViewById(R.id.movie_studio);
            rating = itemView.findViewById(R.id.movie_rating);
            poster = itemView.findViewById(R.id.movie_poster);
            edit = itemView.findViewById(R.id.movie_edit);
            delete = itemView.findViewById(R.id.movie_delete);
        }
    }
}
