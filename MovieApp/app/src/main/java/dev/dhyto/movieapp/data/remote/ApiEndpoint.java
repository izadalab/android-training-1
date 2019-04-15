package dev.dhyto.movieapp.data.remote;

import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.data.model.TrailerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailersByMovieId(
            @Query("api_key") String apiKey,
            @Path("movie_id") int movieId
    );
}
