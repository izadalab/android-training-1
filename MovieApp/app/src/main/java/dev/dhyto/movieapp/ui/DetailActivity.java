package dev.dhyto.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.common.Constants;
import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.data.model.TrailerResponse;
import dev.dhyto.movieapp.data.remote.MovieService;
import dev.dhyto.movieapp.databinding.ActivityDetailBinding;
import dev.dhyto.movieapp.ui.adapter.TrailerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieResponse.Movie movie = getIntent().getParcelableExtra("movie_intent");

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        displayDetail(movie);
        initRecyclerView();
        displayTrailers(movie.getId());
    }

    private void displayDetail(MovieResponse.Movie movie){
        activityDetailBinding.detailTop.sectionTitle.movieTitle.setText(movie.getTitle());
        activityDetailBinding.detailTop.sectionTitle.movieReleaseDate.setText(movie.getReleaseDate());

        activityDetailBinding.detailBottom.synopsis.setText(movie.getOverview());

        Glide.with(this).
                load(Constants.POSTER_BASE_URL + movie.getPosterPath())
                .into(activityDetailBinding.detailTop.posterImage);

        Glide.with(this).
                load(Constants.BACKDROP_BASE_URL + movie.getBackdropPath())
                .into(activityDetailBinding.detailTop.backropImage);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL,
                        false);
        activityDetailBinding.detailBottom.rvTrailers.setLayoutManager(layoutManager);
    }

    private void displayTrailers(int movieId){
        MovieService.getAPI().getTrailersByMovieId(movieId,
                "678ef42a1b584848591cbd02ac3899c3").enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if(response.isSuccessful()) {
                    List<TrailerResponse.ResultsTrailer> trailers = response.body().getResults();
                    TrailerAdapter adapter = new TrailerAdapter(trailers);
                    activityDetailBinding.detailBottom.rvTrailers.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
