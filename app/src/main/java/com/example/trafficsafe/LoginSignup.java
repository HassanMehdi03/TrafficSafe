package com.example.trafficsafe;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class LoginSignup extends AppCompatActivity {

    private Fragment loginFragment,signupFragment;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        init();
        manager.beginTransaction().show(loginFragment).hide(signupFragment).commit();
    }

    private void init()
    {
        manager=getSupportFragmentManager();
        loginFragment=manager.findFragmentById(R.id.loginFrag);
        signupFragment=manager.findFragmentById(R.id.signupFrag);
    }
}