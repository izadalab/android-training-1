package dev.dhyto.movieapp.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dev.dhyto.movieapp.data.model.MovieResponse;

@Dao
public interface MovieDao {

    @Insert
    void insertMovie(MovieResponse.Movie movie);

    @Query("SELECT * FROM movie")
    List<MovieResponse.Movie> getAllMovies();

   @Query("SELECT COUNT(*) FROM movie WHERE id = :id")
   int countMovieById(int id);

    @Query("DELETE FROM movie WHERE id = :id")
    void deleteMovie(int id);


}
