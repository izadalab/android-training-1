package dev.dhyto.movieapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;

import java.util.List;

import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.common.Constants;
import dev.dhyto.movieapp.data.local.MovieDao;
import dev.dhyto.movieapp.data.local.MovieDatabase;
import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.data.model.TrailerResponse;
import dev.dhyto.movieapp.data.remote.MovieService;
import dev.dhyto.movieapp.databinding.ActivityDetailBinding;
import dev.dhyto.movieapp.ui.adapter.TrailerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding;
    private boolean isFavorite = false;

    private MovieDatabase movieDatabase;

    private MovieResponse.Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = getIntent().getParcelableExtra("movie_intent");

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        movieDatabase = MovieDatabase.getDatabase(this);

        initToolbar(movie);
        displayDetail(movie);
        initRecyclerView();
        displayTrailers(movie.getId());
//        findMovieById(movie.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.favorite_menu) {
            if (isFavorite) {
                item.setIcon(R.drawable.ic_favorite_border_black_24dp);
                deleteFromFavorite(movie.getId());
                isFavorite = false ;
            } else {
                item.setIcon(R.drawable.ic_favorite_black_24dp);
                addToFavorite(movie);
                isFavorite =true ;
            }

        }


        return super.onOptionsItemSelected(item);

    }

    private void initToolbar(MovieResponse.Movie movie) {
        activityDetailBinding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbarTitle);
        activityDetailBinding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbarTitle);
//        activityDetailBinding.collapsingToolbar.setContentScrimColor(getResources().getColor(android.R.color.transparent));

        if (activityDetailBinding.toolbar != null) {
            setSupportActionBar(activityDetailBinding.toolbar);
            getSupportActionBar().setTitle(movie.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void displayDetail(MovieResponse.Movie movie) {
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

    private void displayTrailers(int movieId) {
        MovieService.getAPI().getTrailersByMovieId(movieId,
                "678ef42a1b584848591cbd02ac3899c3").enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
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

    private void addToFavorite(MovieResponse.Movie movie) {
        MovieDao movieDao = movieDatabase.movieDao();
        try {
            movieDao.insertMovie(movie);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("addError", e.getMessage());
        }
    }

    private void deleteFromFavorite(int id) {
        MovieDao movieDao = movieDatabase.movieDao();
        movieDao.deleteMovie(id);
    }

    private void findMovieById(int id) {
        MovieDao movieDao = movieDatabase.movieDao();

        try {
            MovieResponse.Movie movie = movieDao.findMovieById(id);
            if (movie == null) {
                isFavorite = false;
            } else {
                isFavorite = true;
            }
        } catch (Exception e) {

        }


        Log.d("isFavorite", String.valueOf(isFavorite));
    }
}
