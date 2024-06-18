package com.example.trafficsafe;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class LoginSignup extends AppCompatActivity {

    private Fragment loginFragment,signupFragment;
    private View login;
    private TextView tvRegister;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        tvRegister.setOnClickListener(v -> {
            manager.beginTransaction().hide(loginFragment).show(signupFragment).commit();
        });

    }

    private void init()
    {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        manager=getSupportFragmentManager();
        loginFragment=manager.findFragmentById(R.id.loginFrag);
        signupFragment=manager.findFragmentById(R.id.signupFrag);

        manager.beginTransaction().show(loginFragment).hide(signupFragment).commit();

        login=loginFragment.getView();
        tvRegister=login.findViewById(R.id.tvRegister);


    }
}