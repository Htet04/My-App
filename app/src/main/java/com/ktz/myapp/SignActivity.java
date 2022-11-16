package com.ktz.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.ktz.myapp.databinding.ActivitySignBinding;
import com.ktz.myapp.fragment.sign.SignUpFragment;

public class SignActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignBinding binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("sign",MODE_PRIVATE);
        if (preferences.getBoolean("signIn",false)){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.signMainFrame, new SignUpFragment()).commit();
        }
    }
}