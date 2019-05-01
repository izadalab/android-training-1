package dev.dhyto.movieapp.ui;


import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.databinding.FragmentMoviesBinding;
import dev.dhyto.movieapp.ui.adapter.MovieAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    protected MovieAdapter adapter;

    protected FragmentMoviesBinding fragmentMoviesBinding;

    protected List<MovieResponse.Movie> movies = new ArrayList<>();

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMoviesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        View view = fragmentMoviesBinding.getRoot();

        initViews();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (savedInstanceState != null) {
            List<MovieResponse.Movie> movies = savedInstanceState.getParcelableArrayList("movies");
            displayMovies(movies);
        } else {
            getMovies();
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movies", (ArrayList<? extends Parcelable>) movies);
    }

    private void initViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        fragmentMoviesBinding.rvMovies.setLayoutManager(gridLayoutManager);
        adapter = new MovieAdapter(movies, getContext());
        fragmentMoviesBinding.rvMovies.setAdapter(adapter);

    }

    protected abstract void getMovies();

    protected void getMoviesFromRemote(Call<MovieResponse> call) {
        fragmentMoviesBinding.progressbar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<MovieResponse.Movie> movieList = response.body().getResults();
                    displayMovies(movieList);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("error", t.getMessage());
                fragmentMoviesBinding.progressbar.setVisibility(View.GONE);
            }
        });
    }

    protected void displayMovies(List<MovieResponse.Movie> movieList) {
        fragmentMoviesBinding.progressbar.setVisibility(View.GONE);
        movies.clear();
        movies.addAll(movieList);
        adapter.notifyDataSetChanged();
    }

}
