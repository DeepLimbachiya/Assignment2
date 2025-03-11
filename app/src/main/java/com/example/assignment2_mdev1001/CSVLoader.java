package com.example.assignment2_mdev1001;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class CSVLoader {
    public static List<Movie> loadMoviesFromCSV(Context context, int resourceId) {
        List<Movie> movies = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resourceId)));
            String line;
            boolean firstLine = true; // Skip header row if necessary
            Log.d("CSV_LOADER", "Reading CSV file...");

            while ((line = reader.readLine()) != null) {
                if (firstLine) { // Skip CSV header
                    firstLine = false;
                    continue;
                }

                Log.d("CSV_LOADER", "Read line: " + line);
                String[] columns = line.split(",");

                if (columns.length == 4) {
                    String title = columns[0].trim();
                    String studio = columns[1].trim();
                    String posterUrl = columns[2].trim();
                    float rating;

                    try {
                        rating = Float.parseFloat(columns[3].trim());
                    } catch (NumberFormatException e) {
                        Log.e("CSV_LOADER", "Error parsing rating for: " + title);
                        continue; // Skip this entry if rating is invalid
                    }

                    movies.add(new Movie(title, studio, posterUrl, rating));
                    Log.d("CSV_LOADER", "Added movie: " + title);
                } else {
                    Log.e("CSV_LOADER", "Invalid CSV row format: " + line);
                }
            }
            reader.close();
        } catch (Exception e) {
            Log.e("CSV_LOADER", "Error reading CSV file", e);
        }

        Log.d("CSV_LOADER", "Total movies loaded: " + movies.size());
        return movies;
    }
}
