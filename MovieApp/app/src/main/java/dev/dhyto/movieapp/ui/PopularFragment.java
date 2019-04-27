package dev.dhyto.movieapp.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.data.remote.MovieService;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends BaseFragment {


    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    protected void displayMovies() {
        getMoviesFromRemote(MovieService.getAPI().getPopularMovies("678ef42a1b584848591cbd02ac3899c3"));
    }


}
