package com.example.assignment2_mdev1001;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movies ORDER BY title ASC")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    Movie getMovieById(int movieId);
}

