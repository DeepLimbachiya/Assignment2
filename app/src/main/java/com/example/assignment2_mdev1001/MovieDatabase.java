package com.example.assignment2_mdev1001;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false) // ✅ Ensure version is set
public abstract class MovieDatabase extends RoomDatabase {
    private static volatile MovieDatabase instance;

    public abstract MovieDao movieDao(); // ✅ Ensure this method exists

    public static MovieDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (MovieDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    MovieDatabase.class, "movie_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }

    static void populateDatabase(MovieDatabase db) {
        MovieDao movieDao = db.movieDao();
        movieDao.insert(new Movie("Inception", "Warner Bros.", "https://image.tmdb.org/t/p/w500/ty8TGRuvJLPUmARH1nRIsqwvim.jpg", 8.8f));

        movieDao.insert(new Movie("Interstellar", "Paramount", "https://image.tmdb.org/t/p/w500/6FCtAuVAW8XjZ7eWeLibRLWTW.jpg", 8.8f));

    }

}
