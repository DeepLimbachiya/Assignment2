package com.example.assignment2_mdev1001;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String studio;
    private String posterUrl;
    private float rating;

    public Movie(String title, String studio, String posterUrl, float rating) {
        this.title = title;
        this.studio = studio;
        this.posterUrl = posterUrl;
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public String getStudio() { return studio; }
    public String getPosterUrl() { return posterUrl; }
    public float getRating() { return rating; }

}
