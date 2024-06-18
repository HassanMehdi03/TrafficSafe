package com.example.trafficsafe.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.trafficsafe.HomePage;
import com.example.trafficsafe.R;

public class LoginFrag extends Fragment {

    private Button btnSignIn;

    public LoginFrag() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), HomePage.class));
                requireActivity().finish();
            }
        });

    }

    private void init()
    {
        btnSignIn=getView().findViewById(R.id.btnSignin);
    }

    private void fingerprintEnableDialog()
    {
        View v=LayoutInflater.from(getContext()).inflate(R.layout.fingerprint_enable_custom_dialog,null,false);
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(v);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
    }

    private void fingerprintNotEnableDialog()
    {
        View v=LayoutInflater.from(getContext()).inflate(R.layout.fingerprint_not_enable_custom_design,null,false);
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(v);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}