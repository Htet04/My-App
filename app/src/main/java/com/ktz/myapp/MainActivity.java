package com.ktz.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.ktz.myapp.database.DataBaseHelper;
import com.ktz.myapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences preferences;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("sign",MODE_PRIVATE);
        db = new DataBaseHelper(this);
        binding.mText.setText("Hello "+db.getValue(preferences.getLong("userId",1),DataBaseHelper.KEY_NAME)+"\n"+
                "Email: "+db.getValue(preferences.getLong("userId",1),DataBaseHelper.KEY_EMAIL)+"\n"+
                "Phone: "+db.getValue(preferences.getLong("userId",1),DataBaseHelper.KEY_PHONE)+"\n"+
                "Country: "+db.getValue(preferences.getLong("userId",1),DataBaseHelper.KEY_COUNTRY)+"\n"+
                "Age: "+db.getValue(preferences.getLong("userId",1),DataBaseHelper.KEY_AGE)+"\n"+
                "Gender: "+db.getValue(preferences.getLong("userId",1),DataBaseHelper.KEY_GENDER));
        binding.btndeleteAcc.setOnClickListener(v -> {
            db.deleteUser(preferences.getLong("userId",1));
            preferences.edit().putBoolean("signIn",false).commit();
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            finish();
        });
        binding.btnSignOut.setOnClickListener(v -> {
            preferences.edit().putBoolean("signIn",false).commit();
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}