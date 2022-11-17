package com.ktz.myapp.fragment;

import static com.ktz.myapp.Utils.chByType;
import static com.ktz.myapp.Utils.clearErrorText;
import static com.ktz.myapp.Utils.isContainSpecialChar;
import static com.ktz.myapp.Utils.isEmailValidation;
import static com.ktz.myapp.Utils.isEmpty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ktz.myapp.MainActivity;
import com.ktz.myapp.R;
import com.ktz.myapp.database.DataBaseHelper;
import com.ktz.myapp.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;
    DataBaseHelper db;
    SharedPreferences preferences;
    TextInputLayout nameLayout, emailLayout, passLayout, phoneLayout, ageLayout, genderLayout, countryLayout;
    TextInputEditText name, email, pass, phone;
    MaterialAutoCompleteTextView country, gender, age;
    CardView chUpper, chLower, chNum, chSpecial, chLength;
    boolean up, lo, num, special, length;
    MaterialButton btnSignUp;
    TextView btnLogin;

    public SignUpFragment() {
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

        String[] countries = getResources().getStringArray(R.array.country);
        String[] arr2 = getResources().getStringArray(R.array.gender);
        String[] ages = new String[80];
        for (int i = 0; i < 80; i++) {
            ages[i] = String.valueOf(i + 18);
        }
        age.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ages));
        country.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countries));
        gender.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arr2));
        return binding.getRoot();
    }

    private void initialize() {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater());
        db = new DataBaseHelper(getContext());
        preferences = getContext().getSharedPreferences("sign", Context.MODE_PRIVATE);

        nameLayout = binding.nameLayout;
        emailLayout = binding.emailLayout;
        passLayout = binding.passLayout;
        phoneLayout = binding.phoneLayout;
        ageLayout = binding.ageLayout;
        genderLayout = binding.genderLayout;
        countryLayout = binding.countryLayout;

        name = binding.name;
        email = binding.email;
        pass = binding.pass;
        phone = binding.phone;
        age = binding.age;
        gender = binding.gender;
        country = binding.country;

        chUpper = binding.chUpper;
        chLower = binding.chLower;
        chNum = binding.chNum;
        chSpecial = binding.chSpecial;
        chLength = binding.chLength;
        btnSignUp = binding.btnSignUp;
        btnLogin = binding.btnSignIn;
    }

    private void functions() {
        clearErrorText(nameLayout);
        clearErrorText(emailLayout);
        clearErrorText(passLayout);
        clearErrorText(ageLayout);
        clearErrorText(phoneLayout);
        clearErrorText(genderLayout);
        clearErrorText(countryLayout);
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
            String Name = name.getText().toString(),
                    Email = email.getText().toString(),
                    Pass = pass.getText().toString(),
                    Phone = phone.getText().toString(),
                    Age = age.getText().toString(),
                    Gender = gender.getText().toString(),
                    Country = country.getText().toString();

            if (Name.isEmpty()) {
                showError(nameLayout, "Name can't be empty!");
            }
            if (Age.isEmpty()) {
                showError(ageLayout, "Age can't be empty!");
            }
            if (Gender.isEmpty()) {
                showError(genderLayout, "Gender can't be empty!");
            }
            if (Country.isEmpty()) {
                showError(countryLayout, "Country can't be empty!");
            }
            if (Phone.isEmpty()) {
                showError(phoneLayout, "Phone can't be empty!");
            }
            if (Email.isEmpty()) {
                showError(emailLayout, "Email can't be empty");
            }
            if (Pass.isEmpty()) {
                showError(passLayout, "Password can't be empty!");
            }

            if (!isEmpty(Name, Email, Pass, Phone, Age, Gender, Country)) {
                if (isEmailValidation(Email)) {
                    long id = db.addUser(Name, Email, Pass, Phone, Age, Gender, Country);
                    preferences.edit().putBoolean("signIn", true).commit();
                    preferences.edit().putLong("userId", id).commit();
                    getActivity().startActivity(new Intent(getContext(), MainActivity.class));
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                } else {
                    showError(emailLayout, "Email invalid!");
                }
            }


            /*
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

            //check is input are empty
            if (!Utils.isEmpty(Name, Email, Pass,Phone,Age,Gender,Country)) {
                //check is Email Valid
                if (isEmailValidation(Email)) {
                    //check is account with this email exist
                    if (isEmailExist(getContext(), Email)) {
                        Toast.makeText(getContext(), "Account with this email exist login instead.", Toast.LENGTH_SHORT).show();
                    } else {
                        // if not, add new user
                        long status = db.addUser(Name, Email, Pass,Phone,Age,Gender,Country);
                        if (status != -1) {
                            //if add success
                            Toast.makeText(getContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                            preferences.edit().putBoolean("signIn", true).commit();
                            preferences.edit().putLong("userId", status).commit();
                            startActivity(new Intent().setClass(getContext(), MainActivity.class).putExtra("userId", status));
                            if (getActivity()!=null) {
                                getActivity().finishAndRemoveTask();
                            }
                        } else {
                            //if not
                            Toast.makeText(getContext(), "Unable to Sign Up", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {

                }
            }*/
        });
        btnLogin.setOnClickListener(v -> {
            new Handler().postDelayed(() -> {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.loginsignupframe, new LoginFragment()).commit();
            }, 250);
        });
    }

    private void sh(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showError(TextInputLayout layout, String msg) {
        layout.setError(msg);
    }
}