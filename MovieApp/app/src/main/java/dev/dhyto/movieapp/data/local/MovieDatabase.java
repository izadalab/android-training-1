package dev.dhyto.movieapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dev.dhyto.movieapp.data.model.MovieResponse;

@Database(entities = {MovieResponse.Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static volatile MovieDatabase instance;

    public abstract MovieDao movieDao();

    public static MovieDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "tmdb_movie")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

}
