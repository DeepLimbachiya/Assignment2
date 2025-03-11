package com.example.assignment2_mdev1001;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieDatabase database;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab_add);

        database = MovieDatabase.getInstance(this);
        populateDatabase(database); // Populate the database

        loadMovies();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditMovieActivity.class);
            startActivity(intent);
        });
    }

    static void populateDatabase(final MovieDatabase db) {
        MovieDao movieDao = db.movieDao();

        // Create sample movies
        Movie movie1 = new Movie("Inception", "Warner Bros.", "https://image.tmdb.org/t/p/w500/ty8TGRuvJLPUmARH1nRIsqwvim.jpg", 8.8f);

        // Insert sample movies into the database
        movieDao.insert(movie1);
        Log.d("DATABASE_CHECK", "Inserted 1 movie into database.");
    }

    private void loadMovies() {
        List<Movie> movies = database.movieDao().getAllMovies();
        Log.d("MainActivity", "Movies loaded: " + movies.size()); // Debugging line

        adapter = new MovieAdapter(movies, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onEditClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, AddEditMovieActivity.class);
                intent.putExtra("movie_id", movie.getId());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(Movie movie) {
                database.movieDao().delete(movie);
                loadMovies();
                Toast.makeText(MainActivity.this, "Movie Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}