package com.ktz.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ktz.myapp.databinding.ActivitySignBinding;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignBinding binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}