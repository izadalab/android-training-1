package dev.dhyto.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.databinding.ActivityDetailBinding;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
    }
}
