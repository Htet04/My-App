package com.ktz.myapp.fragment.sign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ktz.myapp.R;
import com.ktz.myapp.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;
    TextInputLayout nameLayout,emailLayout,passLayout;
    TextInputEditText name,email,pass;
    MaterialButton btnSignUp,btnSignIn;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater());
        // initialization of View Objects
        initializer();



        return binding.getRoot();
    }
    private void initializer(){
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