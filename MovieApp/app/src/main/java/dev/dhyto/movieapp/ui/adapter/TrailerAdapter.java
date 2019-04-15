package dev.dhyto.movieapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.data.model.TrailerResponse;
import dev.dhyto.movieapp.databinding.TrailerListItemBinding;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private List<TrailerResponse.ResultsTrailer> trailers;
    private Context context ;

    public TrailerAdapter(List<TrailerResponse.ResultsTrailer> trailers) {
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.trailer_list_item, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        TrailerResponse.ResultsTrailer trailer = trailers.get(position);

        Glide.with(context)
                .load("https://i1.ytimg.com/vi/" + trailer.getKey() + "/0.jpg")
                .into(holder.viewDataBinding.trailerThumbnail);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder {
         TrailerListItemBinding viewDataBinding ;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            viewDataBinding = DataBindingUtil.bind(itemView);
        }
    }
}
