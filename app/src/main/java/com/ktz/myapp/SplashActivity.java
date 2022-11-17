package com.ktz.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.ktz.myapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("sign",MODE_PRIVATE);
        new Handler().postDelayed(() -> {
            if (preferences.getBoolean("signIn",false)){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(),LoginSignUpActivity.class));
                finish();
            }
        },3000);
    }

    @Override
    public void onBackPressed() {

    }
}