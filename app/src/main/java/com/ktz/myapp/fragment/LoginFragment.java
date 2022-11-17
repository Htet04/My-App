package com.ktz.myapp.fragment;

import static com.ktz.myapp.Utils.clearErrorText;
import static com.ktz.myapp.Utils.isEmailValidation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ktz.myapp.MainActivity;
import com.ktz.myapp.R;
import com.ktz.myapp.Utils;
import com.ktz.myapp.database.DataBaseHelper;
import com.ktz.myapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    DataBaseHelper db;
    SharedPreferences preferences;
    TextInputLayout nameLayout, emailLayout, passLayout;
    TextInputEditText name, email, pass;
    MaterialButton btnLogin;
    TextView btnSignUp;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        functions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return binding.getRoot();
    }

    private void initialize() {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        db = new DataBaseHelper(getContext());
        preferences = getContext().getSharedPreferences("sign", Context.MODE_PRIVATE);
        nameLayout = binding.nameLayout;
        emailLayout = binding.emailLayout;
        passLayout = binding.passLayout;
        name = binding.name;
        email = binding.email;
        pass = binding.pass;
        btnSignUp = binding.btnSignUp;
        btnLogin = binding.btnSignIn;
    }

    private void functions() {
        clearErrorText(nameLayout);
        clearErrorText(emailLayout);
        clearErrorText(passLayout);
        btnLogin.setOnClickListener(v -> {
            String Name = name.getText().toString(), Email = email.getText().toString(), Pass = pass.getText().toString();
            if (Name.isEmpty()) {
                nameLayout.setError("Name can't be empty!");
            }
            if (Email.isEmpty()) {
                emailLayout.setError("Email can't be empty!");
            }
            if (!isEmailValidation(Email)) {
                emailLayout.setError("Email is invalid!");
            }
            if (Pass.isEmpty()) {
                passLayout.setError("Password can't be empty!");
            }
            if (!Utils.isEmpty(Name, Email, Pass)) {
                //do the stuff here
                for (int i = 1; i <= db.userCount(); i++) {
                    if (Name.equals(db.getValue(i, DataBaseHelper.KEY_NAME)) && Email.equals(db.getValue(i, DataBaseHelper.KEY_EMAIL)) && Pass.equals(db.getValue(i, DataBaseHelper.KEY_PASS))) {
                        Toast.makeText(getContext(), "Sign In Success", Toast.LENGTH_SHORT).show();
                        preferences.edit().putBoolean("signIn", true).commit();
                        preferences.edit().putLong("userId", (long) i).commit();
                        getActivity().startActivity(new Intent(getContext(), MainActivity.class).putExtra("userId", i));
                        if (getActivity() != null) {
                            getActivity().finish();
                        }
                        break;
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(v -> {
            new Handler().postDelayed(() -> {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.loginsignupframe, new SignUpFragment()).commit();
            }, 250);
        });
    }
}