package com.example.assignment2_mdev1001;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditMovieActivity extends AppCompatActivity {
    private EditText titleInput, studioInput, ratingInput, posterInput;
    private Button saveButton, cancelButton;
    private MovieDatabase database;
    private int movieId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);

        titleInput = findViewById(R.id.input_title);
        studioInput = findViewById(R.id.input_studio);
        ratingInput = findViewById(R.id.input_rating);
        posterInput = findViewById(R.id.input_poster);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);

        database = MovieDatabase.getInstance(this);

        if (getIntent().hasExtra("movie_id")) {
            movieId = getIntent().getIntExtra("movie_id", -1);
            Movie movie = database.movieDao().getMovieById(movieId);
            if (movie != null) {
                titleInput.setText(movie.getTitle());
                studioInput.setText(movie.getStudio());
                ratingInput.setText(String.valueOf(movie.getRating()));
                posterInput.setText(movie.getPosterUrl());
            }
        }

        saveButton.setOnClickListener(v -> saveMovie());
        cancelButton.setOnClickListener(v -> finish());
    }

    private void saveMovie() {
        String title = titleInput.getText().toString();
        String studio = studioInput.getText().toString();
        float rating = Float.parseFloat(ratingInput.getText().toString());
        String posterUrl = posterInput.getText().toString();

        if (title.isEmpty() || studio.isEmpty() || posterUrl.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (movieId == -1) {
            database.movieDao().insert(new Movie(title, studio, posterUrl, rating));
        } else {
            Movie movie = new Movie(title, studio, posterUrl, rating);
            movie.setId(movieId);
            database.movieDao().update(movie);
        }
        Toast.makeText(this, "Movie Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
