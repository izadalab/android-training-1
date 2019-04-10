package dev.dhyto.movieapp.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static ApiEndpoint getAPI() {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(ApiEndpoint.class);

    }

}
