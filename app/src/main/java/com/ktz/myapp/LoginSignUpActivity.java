package com.ktz.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.ktz.myapp.databinding.ActivityLoginSignUpBinding;
import com.ktz.myapp.fragment.LoginFragment;
import com.ktz.myapp.fragment.SignUpFragment;

public class LoginSignUpActivity extends AppCompatActivity {

    ActivityLoginSignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.loginsignupframe,new SignUpFragment()).commit();
    }
}