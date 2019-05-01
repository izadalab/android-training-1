package dev.dhyto.movieapp.ui;

import java.util.List;

import dev.dhyto.movieapp.data.local.MovieDao;
import dev.dhyto.movieapp.data.local.MovieDatabase;
import dev.dhyto.movieapp.data.model.MovieResponse;

public class FavoriteFragment extends BaseFragment {

    public FavoriteFragment() {

    }

    @Override
    protected void getMovies() {
        getAllMovies();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMovies();
    }

    private void getAllMovies() {
        MovieDatabase movieDatabase = MovieDatabase.getDatabase(getActivity());
        MovieDao movieDao = movieDatabase.movieDao();
        try {
            List<MovieResponse.Movie> movies = movieDao.getAllMovies();
            displayMovies(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
