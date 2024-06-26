package com.example.trafficsafe.Fragments;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.trafficsafe.HomePage;
import com.example.trafficsafe.LoginSignup;
import com.example.trafficsafe.R;

public class SettingFrag extends Fragment {

    private ImageView ivFacebook, ivTwitter, ivYoutube, ivInstagram;
    private Button btnLogout;
    private Switch swTouchId;
    private SharedPreferences sPref;
    private SharedPreferences.Editor editor;

    public SettingFrag()
    {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setSocialMediaClickListeners();
        setLogoutClickListener();
        buttonsStateCheckers();

        swTouchId.setOnClickListener(v->touchIDEnable());

    }

    private void buttonsStateCheckers() {
        boolean isTouchIdOn = sPref.getBoolean("Key_touchIdIsOn", false);
        swTouchId.setChecked(isTouchIdOn);
    }

    private void touchIDEnable()
    {
        if(swTouchId.isChecked())
        {
            editor.putBoolean("Key_touchIdIsOn",true);
        }
        else
        {
            editor.putBoolean("Key_touchIdIsOn",false);
        }

        editor.apply();

    }

    private void setLogoutClickListener() {
        btnLogout.setOnClickListener(v -> showLogoutDialog());
    }

    private void showLogoutDialog() {

        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.logout_custom_dialog, null,false);

        Button btnDLogout = dialogView.findViewById(R.id.btnDLogout);
        Button btnDCancel = dialogView.findViewById(R.id.btnDCancel);


        // Show dialog with custom logout view
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(dialogView);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        btnDCancel.setOnClickListener(v -> dialog.dismiss());

        btnDLogout.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(requireActivity(), LoginSignup.class));
            requireActivity().finish();
        });
    }

    private void setSocialMediaClickListeners() {
        try {
            ivFacebook.setOnClickListener(v -> openUrl("https://www.facebook.com/Hassan.Mehdi.03"));
            ivTwitter.setOnClickListener(v -> openUrl("https://x.com/HassanMehdi03"));
            ivYoutube.setOnClickListener(v -> openUrl("https://www.youtube.com/@Has_san03"));
            ivInstagram.setOnClickListener(v -> openUrl("https://www.instagram.com/hassan._.mehdi/"));
        } catch (Exception e) {
            Log.e("SettingsFrag", "Error setting social media click listeners: " + e.getMessage());
        }
    }

    private void openUrl(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    private void init(View view) {
        ivFacebook = view.findViewById(R.id.ivFacebook);
        ivTwitter = view.findViewById(R.id.ivTwitter);
        ivYoutube = view.findViewById(R.id.ivYoutube);
        ivInstagram = view.findViewById(R.id.ivInstagram);
        btnLogout = view.findViewById(R.id.btnLogout);
        swTouchId= view.findViewById(R.id.touchId);
        sPref = requireContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        editor = sPref.edit();
    }


}
