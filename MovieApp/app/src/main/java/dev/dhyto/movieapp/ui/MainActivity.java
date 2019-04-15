package dev.dhyto.movieapp.ui;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.data.remote.MovieService;
import dev.dhyto.movieapp.databinding.ActivityMainBinding;
import dev.dhyto.movieapp.ui.adapter.MovieAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        activityMainBinding.rvMovies.setLayoutManager(gridLayoutManager);

        MovieService.getAPI().getNowPlayingMovies("678ef42a1b584848591cbd02ac3899c3").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<MovieResponse.Movie> movies = response.body().getResults();
                    adapter = new MovieAdapter(movies, MainActivity.this);
                    activityMainBinding.rvMovies.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


    }
}
