package com.example.trafficsafe.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trafficsafe.HomePage;
import com.example.trafficsafe.R;

import java.util.concurrent.Executor;

public class LoginFrag extends Fragment {

    private Button btnSignIn;
    private TextView tvRegister;
    private ImageView ivFingerprint;
    private SharedPreferences sPref;
    private SharedPreferences.Editor editor;


    public LoginFrag() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

//          internetDialog();

        btnSignIn.setOnClickListener(v->moveToHomePage());
        tvRegister.setOnClickListener(v->moveToSignUpFrag());
        ivFingerprint.setOnClickListener(v->showFingerprintDialog());

    }

    private void showFingerprintDialog()
    {
        if(sPref.getBoolean("Key_touchIdIsOn",true))
        {
            fingerprintEnableDialog();
        }
        else
        {
            fingerprintNotEnableDialog();
        }
    }

    private void init(View view)
    {
        btnSignIn=view.findViewById(R.id.btnSignin);
        tvRegister=view.findViewById(R.id.tvRegister);
        ivFingerprint=view.findViewById(R.id.ivFingerprint);
        sPref = requireContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        editor = sPref.edit();
    }

    private void moveToSignUpFrag()
    {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main,new SignupFrag()).commit();
    }

    private void moveToHomePage()
    {
        startActivity(new Intent(requireActivity(), HomePage.class));
        requireActivity().finish();
    }

    private void internetDialog()
    {
        View v=LayoutInflater.from(getContext()).inflate(R.layout.no_internet_custom_dialog,null,false);

        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(v);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        Button btnOk;

        btnOk=v.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void fingerprintEnableDialog()
    {
        View v=LayoutInflater.from(getContext()).inflate(R.layout.fingerprint_enable_custom_dialog,null,false);

        ImageView ivDialogFingerprint;
        ivDialogFingerprint=v.findViewById(R.id.ivFingerprintColored);

        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(v);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

        BiometricVerification(dialog,ivDialogFingerprint);
    }

    private void BiometricVerification(Dialog dialog,ImageView ivDialogFingerprint)
    {
        BiometricManager biometricManager=BiometricManager.from(requireContext());

        if(biometricManager.canAuthenticate()==BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE)
        {
            Toast.makeText(requireContext(), "No biometric features available on this device.", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }

        Executor executor= ContextCompat.getMainExecutor(requireContext());

        BiometricPrompt biometricPrompt=new BiometricPrompt(requireActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                dialog.dismiss();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                ivDialogFingerprint.setImageResource(R.drawable.ic_tick);
                moveToHomePage();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                ivDialogFingerprint.setImageResource(R.drawable.ic_invalid);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivDialogFingerprint.setImageResource(R.drawable.ic_fingerprint_colored);
                    }
                },  1000);
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setNegativeButtonText("Cancel")
                .build();
        biometricPrompt.authenticate(promptInfo);
    }

    private void fingerprintNotEnableDialog()
    {
        View v=LayoutInflater.from(getContext()).inflate(R.layout.fingerprint_not_enable_custom_design,null,false);

        Button btnOk=v.findViewById(R.id.btnOk);

        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(v);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        btnOk.setOnClickListener(v1 -> dialog.dismiss());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}