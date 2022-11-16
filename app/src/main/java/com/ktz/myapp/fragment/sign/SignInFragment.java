package com.ktz.myapp.fragment.sign;

import static com.ktz.myapp.Utils.clearErrorText;
import static com.ktz.myapp.Utils.isEmailValidation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ktz.myapp.MainActivity;
import com.ktz.myapp.R;
import com.ktz.myapp.SignActivity;
import com.ktz.myapp.Utils;
import com.ktz.myapp.database.DataBaseHelper;
import com.ktz.myapp.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;
    DataBaseHelper db;
    SharedPreferences preferences;
    TextInputLayout nameLayout, emailLayout, passLayout;
    TextInputEditText name, email, pass;
    MaterialButton btnSignUp, btnSignIn;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater());
        // initialization of View Objects
        initializer();
        finalFun();
        return binding.getRoot();
    }

    private void finalFun() {
        clearErrorText(nameLayout);
        clearErrorText(emailLayout);
        clearErrorText(passLayout);
        btnSignIn.setOnClickListener(v -> {
            String Name = name.getText().toString(), Email = email.getText().toString(), Pass = pass.getText().toString();
            if (Name.isEmpty()) {
                nameLayout.setError("Name can't be empty!");
            }
            if (Email.isEmpty()) {
                emailLayout.setError("Email can't be empty!");
            }
            if (!isEmailValidation(Email)){
                emailLayout.setError("Email is invalid!");
            }
            if (Pass.isEmpty()) {
                passLayout.setError("Password can't be empty!");
            }
            if (!Utils.isEmpty(name, email, pass)) {
                //do the stuff here
                for (int i = 1; i <= db.userCount(); i++) {
                    if (Name.equals(db.getValue(i, DataBaseHelper.KEY_NAME))&&Email.equals(db.getValue(i,DataBaseHelper.KEY_EMAIL))&&Pass.equals(db.getValue(i,DataBaseHelper.KEY_PASS))){
                        Toast.makeText(getContext(), "Sign In Success", Toast.LENGTH_SHORT).show();
                        preferences.edit().putBoolean("signIn",true).commit();
                        getActivity().startActivity(new Intent(getContext(), MainActivity.class).putExtra("userId",i));
                        getActivity().finish();
                        new SignUpFragment().onDestroy();
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.signMainFrame, new SignUpFragment()).commit());
    }

    private void initializer() {
        db = new DataBaseHelper(getContext());
        preferences = getContext().getSharedPreferences("sign", Context.MODE_PRIVATE);
        nameLayout = binding.nameLayout;
        emailLayout = binding.emailLayout;
        passLayout = binding.passLayout;
        name = binding.name;
        email = binding.email;
        pass = binding.pass;
        btnSignUp = binding.btnSignUp;
        btnSignIn = binding.btnSignIn;
    }
}