package com.ktz.myapp.fragment.sign;

import static com.ktz.myapp.Utils.clearErrorText;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ktz.myapp.R;
import com.ktz.myapp.Utils;
import com.ktz.myapp.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;
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
            String getName = name.getText().toString(), getEmail = email.getText().toString(), getPass = pass.getText().toString();
            if (getName.isEmpty()) {
                nameLayout.setError("Name can't be empty!");
            }
            if (getEmail.isEmpty()) {
                emailLayout.setError("Email can't be empty!");
            }
            if (getPass.isEmpty()) {
                passLayout.setError("Password can't be empty!");
            }
            if (!Utils.isEmpty(name, email, pass)) {
                //do the stuff here
            }
        });
        btnSignUp.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.signMainFrame, new SignUpFragment()).commit());
    }

    private void initializer() {
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