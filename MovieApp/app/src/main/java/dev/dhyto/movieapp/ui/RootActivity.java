package dev.dhyto.movieapp.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

        setFragment(new NowPlayingFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;

        switch (menuItem.getItemId()) {
            case R.id.now_playing_menu:
                toolbar.setTitle(getResources().getString(R.string.now_playing));
                fragment = new NowPlayingFragment();
                setFragment(fragment);
                return true;
            case R.id.popular_menu:
                toolbar.setTitle(getResources().getString(R.string.popular));
                return true;
            case R.id.favorite_menu:
                toolbar.setTitle(getResources().getString(R.string.favorite));
                return true;
        }

        return false;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
