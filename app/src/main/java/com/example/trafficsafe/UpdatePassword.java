package com.example.trafficsafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trafficsafe.Fragments.ProfileFrag;

public class UpdatePassword extends AppCompatActivity {

    private ImageView ivHome,ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        init();

        ivHome.setOnClickListener(v -> backToHome());
        ivBack.setOnClickListener(v -> backToUpdatePassword());

        backPressed();

    }

    private void backPressed()
    {
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backToUpdatePassword();
            }
        });
    }


    private void backToHome()
    {
        startActivity(new Intent(UpdatePassword.this,HomePage.class));
        finish();
    }

    private void backToUpdatePassword()
    {
         getSupportFragmentManager().beginTransaction().replace(R.id.main,new ProfileFrag()).commit();
    }

    private void init()
    {

        ivHome = findViewById(R.id.ivHome);
        ivBack = findViewById(R.id.ivBack);

    }
}