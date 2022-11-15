package com.ktz.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ktz.myapp.databinding.ActivitySignBinding;
import com.ktz.myapp.fragment.sign.SignUpFragment;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignBinding binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.signMainFrame,new SignUpFragment()).commit();
    }
}