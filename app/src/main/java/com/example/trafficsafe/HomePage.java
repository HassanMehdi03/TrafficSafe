package com.example.trafficsafe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trafficsafe.Fragments.HistoryFrag;
import com.example.trafficsafe.Fragments.HomeFrag;
import com.example.trafficsafe.Fragments.ProfileFrag;
import com.example.trafficsafe.Fragments.SettingFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationBar;
    private Fragment home, history, profile, setting;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        init();
        initFragment();

        bottomNavigationBar.setOnItemSelectedListener(this::onItemClicked);
    }

    private boolean onItemClicked(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.itemHome) {
            showFragment(home);
        } else if (itemId == R.id.itemHistory) {
            showFragment(history);
        } else if (itemId == R.id.itemProfile) {
            showFragment(profile);
        } else if (itemId == R.id.itemSettings) {
            showFragment(setting);
        }
        return true;
    }

    private void initFragment() {
        home = new HomeFrag();
        history = new HistoryFrag();
        profile = new ProfileFrag();
        setting = new SettingFrag();

        manager.beginTransaction().add(R.id.FragSwitcher, home)
                .add(R.id.FragSwitcher, history).hide(history)
                .add(R.id.FragSwitcher, profile).hide(profile)
                .add(R.id.FragSwitcher, setting).hide(setting)
                .commit();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();

        if (home.isAdded()) {
            transaction.hide(home);
        }
        if (history.isAdded()) {
            transaction.hide(history);
        }
        if (profile.isAdded()) {
            transaction.hide(profile);
        }
        if (setting.isAdded()) {
            transaction.hide(setting);
        }
        transaction.show(fragment).commit();
    }

    private void init()
    {
        manager = getSupportFragmentManager();
        bottomNavigationBar = findViewById(R.id.bottomNavigation);
    }
}
