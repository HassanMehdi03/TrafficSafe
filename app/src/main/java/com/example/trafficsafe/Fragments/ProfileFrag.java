package com.example.trafficsafe.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trafficsafe.R;
import com.example.trafficsafe.UpdatePassword;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFrag extends Fragment {

    private TextInputEditText etPassword;

    public ProfileFrag() {
        // Required empty public constructor
    }

    private void init(View view) {
        etPassword = view.findViewById(R.id.etPassword);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        etPassword.setOnClickListener(v -> changePassword());

    }


    private void changePassword() {
        startActivity(new Intent(getContext(), UpdatePassword.class));
        requireActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
