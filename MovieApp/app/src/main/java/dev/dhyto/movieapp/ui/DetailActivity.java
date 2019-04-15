package dev.dhyto.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.common.Constants;
import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.databinding.ActivityDetailBinding;

import android.os.Bundle;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieResponse.Movie movie = getIntent().getParcelableExtra("movie_intent");

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        displayDetail(movie);
    }

    void displayDetail(MovieResponse.Movie movie){
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
}
