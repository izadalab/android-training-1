package dev.dhyto.movieapp.ui;


import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.List;

import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.data.remote.MovieService;
import dev.dhyto.movieapp.ui.adapter.MovieAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends BaseFragment {

    public NowPlayingFragment() {
    }

    @Override
    protected void displayMovies() {
       getMoviesFromRemote(MovieService.getAPI().getNowPlayingMovies("678ef42a1b584848591cbd02ac3899c3"));
    }
}
