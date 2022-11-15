package com.ktz.myapp.fragment.sign;

import static com.ktz.myapp.Utils.chByType;
import static com.ktz.myapp.Utils.clearErrorText;
import static com.ktz.myapp.Utils.isContainSpecialChar;
import static com.ktz.myapp.Utils.isEmailValidation;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ktz.myapp.R;
import com.ktz.myapp.Utils;
import com.ktz.myapp.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;
    TextInputLayout nameLayout, emailLayout, passLayout;
    TextInputEditText name, email, pass;
    CardView chUpper, chLower, chNum, chSpecial, chLength;
    boolean up, lo, num, special, length;
    MaterialButton btnSignUp, btnSignIn;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater());
        // initialization of View Objects
        initializer();

        finalFun();

        return binding.getRoot();
    }

    private void finalFun() {
        clearErrorText(nameLayout);
        clearErrorText(emailLayout);
        clearErrorText(passLayout);
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                chByType("(.*[A-Z].*)", str, up, chUpper);
                chByType("(.*[a-z].*)", str, lo, chLower);
                chByType("(.*[0-9].*)", str, num, chNum);
                chByType(".{8,20}", str, length, chLength);
                special = isContainSpecialChar(str);
                if (isContainSpecialChar(str)) {
                    chSpecial.setCardBackgroundColor(Color.WHITE);
                } else {
                    chSpecial.setCardBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSignUp.setOnClickListener(v -> {
            String getName = name.getText().toString(), getEmail = email.getText().toString(), getPass = pass.getText().toString();
            if (getName.isEmpty()) {
                nameLayout.setError("Name can't be empty!");
            }
            if (getEmail.isEmpty()) {
                emailLayout.setError("Email can't be empty!");
            }
            if (!isEmailValidation(getContext(),getEmail)){
                emailLayout.setError("Email is invalid!");
            }
            if (getPass.isEmpty()) {
                passLayout.setError("Password can't be empty!");
            }
            if (!Utils.isEmpty(name, email, pass)) {
                //do the stuff here
            }
        });
        btnSignIn.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.signMainFrame, new SignInFragment()).commit());
    }

    private void initializer() {
        nameLayout = binding.nameLayout;
        emailLayout = binding.emailLayout;
        passLayout = binding.passLayout;
        name = binding.name;
        email = binding.email;
        pass = binding.pass;
        chUpper = binding.chUpper;
        chLower = binding.chLower;
        chNum = binding.chNum;
        chSpecial = binding.chSpecial;
        chLength = binding.chLength;
        btnSignUp = binding.btnSignUp;
        btnSignIn = binding.btnSignIn;
    }
}