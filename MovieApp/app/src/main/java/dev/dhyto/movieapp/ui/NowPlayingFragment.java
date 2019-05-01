package dev.dhyto.movieapp.ui;


import androidx.fragment.app.Fragment;

import dev.dhyto.movieapp.data.remote.MovieService;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends BaseFragment {

    public NowPlayingFragment() {
    }

    @Override
    protected void getMovies() {
        getMoviesFromRemote(MovieService.getAPI().getNowPlayingMovies("678ef42a1b584848591cbd02ac3899c3"));
    }
}
