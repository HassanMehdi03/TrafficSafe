package com.example.trafficsafe;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
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
    private boolean homeFragFlag;// for onBackPressed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        init();
        initFragment();

        bottomNavigationBar.setOnItemSelectedListener(this::onItemClicked);
        backPressed();

    }

    private void backPressed() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (homeFragFlag)
                {
                    showLogoutDialog();
                }
                else
                {
                    bottomNavigationBar.setSelectedItemId(R.id.itemHome);
                    showFragment(home);
                }
            }
        });
    }

    private void showLogoutDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.logout_custom_dialog, null, false);
        Button btnDLogout = dialogView.findViewById(R.id.btnDLogout);
        Button btnDCancel = dialogView.findViewById(R.id.btnDCancel);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(dialogView);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        btnDCancel.setOnClickListener(v -> dialog.dismiss());
        btnDLogout.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(this, LoginSignup.class));
            finish();
        });
    }

    private boolean onItemClicked(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.itemHome)
        {
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

        manager.beginTransaction()
                .add(R.id.FragSwitcher, home).hide(home)
                .add(R.id.FragSwitcher, history).hide(history)
                .add(R.id.FragSwitcher, profile).hide(profile)
                .add(R.id.FragSwitcher, setting).hide(setting)
                .commit();

        showFragment(home);

    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();

        if(fragment.equals(home))
        {
            homeFragFlag=true;
        }
        else
        {
            homeFragFlag=false;
        }
        if (home.isAdded()) transaction.hide(home);
        if (history.isAdded()) transaction.hide(history);
        if (profile.isAdded()) transaction.hide(profile);
        if (setting.isAdded()) transaction.hide(setting);

        transaction.show(fragment).commit();
    }

    private void init() {
        manager = getSupportFragmentManager();
        bottomNavigationBar = findViewById(R.id.bottomNavigation);
    }
}
