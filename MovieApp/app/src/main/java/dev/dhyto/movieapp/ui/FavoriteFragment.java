package dev.dhyto.movieapp.ui;

import java.util.List;

import dev.dhyto.movieapp.data.local.MovieDao;
import dev.dhyto.movieapp.data.local.MovieDatabase;
import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.ui.adapter.MovieAdapter;

public class FavoriteFragment extends BaseFragment {

    public FavoriteFragment() {

    }

    @Override
    protected void displayMovies() {
        getAllMovies();
    }

    private void getAllMovies() {
        MovieDatabase movieDatabase = MovieDatabase.getDatabase(getActivity());
        MovieDao movieDao = movieDatabase.movieDao();
        try {
            List<MovieResponse.Movie> movies = movieDao.getAllMovies();
            adapter = new MovieAdapter(movies, getContext());
            fragmentMoviesBinding.rvMovies.setAdapter(adapter);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
