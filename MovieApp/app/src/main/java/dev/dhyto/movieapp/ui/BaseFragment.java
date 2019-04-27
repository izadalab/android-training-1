package dev.dhyto.movieapp.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMoviesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        View view = fragmentMoviesBinding.getRoot();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onStart() {
        super.onStart();
        displayMovies();
    }

    private void initViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        fragmentMoviesBinding.rvMovies.setLayoutManager(gridLayoutManager);
    }

    abstract protected void displayMovies();

    protected void getMoviesFromRemote(Call<MovieResponse> call) {
        fragmentMoviesBinding.progressbar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<MovieResponse.Movie> movies = response.body().getResults();
                    adapter = new MovieAdapter(movies, getContext());
                    fragmentMoviesBinding.rvMovies.setAdapter(adapter);
                    fragmentMoviesBinding.progressbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("error" , t.getMessage());
                fragmentMoviesBinding.progressbar.setVisibility(View.GONE);
            }
        });
    }

}
