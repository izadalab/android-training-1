package dev.dhyto.movieapp.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.dhyto.movieapp.R;
import dev.dhyto.movieapp.databinding.ActivityRootBinding;

public class RootActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityRootBinding activityRootBinding;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRootBinding = DataBindingUtil.setContentView(this, R.layout.activity_root);
        toolbar = getSupportActionBar();

        activityRootBinding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        toolbar.setTitle(getResources().getString(R.string.now_playing));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.now_playing_menu:
                toolbar.setTitle(getResources().getString(R.string.now_playing));
                return true ;
            case R.id.popular_menu:
                toolbar.setTitle(getResources().getString(R.string.popular));
                return true ;
            case R.id.favorite_menu:
                toolbar.setTitle(getResources().getString(R.string.favorite));
                return true ;
        }

        return false;
    }
}
