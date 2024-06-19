package com.example.trafficsafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trafficsafe.Fragments.ProfileFrag;

public class UpdatePassword extends AppCompatActivity {

    private ImageView ivHome,ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        ivHome.setOnClickListener(v -> backToHome());
        ivBack.setOnClickListener(v -> backToUpdatePassword());


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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivHome = findViewById(R.id.ivHome);
        ivBack = findViewById(R.id.ivBack);

    }
}