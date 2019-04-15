package dev.dhyto.movieapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.common.Constants;
import dev.dhyto.movieapp.data.model.MovieResponse;
import dev.dhyto.movieapp.databinding.ListMovieItemBinding;
import dev.dhyto.movieapp.ui.DetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieResponse.Movie> movies;
    private Context context;

    public MovieAdapter(List<MovieResponse.Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {

        final MovieResponse.Movie movie = movies.get(position);

        holder.viewDataBinding.setMovie(movie);

//        holder.viewDataBinding.tvMovieTitle.setText(movie.getTitle());

        Glide.with(context)
                .asBitmap()
                .load(Constants.POSTER_BASE_URL + movie.getPosterPath())
                .into(new BitmapImageViewTarget(holder.viewDataBinding.imgMoviePoster) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(resource, transition);

                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@Nullable Palette palette) {
                                holder.viewDataBinding.titleBackground.setBackgroundColor(
                                        palette.getDarkVibrantColor(context.getResources().getColor(android.R.color.darker_gray)));
                            }
                        });
                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("movie_id", movie.getId());
//                intent.putExtra("movie_title", movie.getTitle());
                intent.putExtra("movie_intent", movie);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ListMovieItemBinding viewDataBinding;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            viewDataBinding = DataBindingUtil.bind(itemView);
        }
    }
}
