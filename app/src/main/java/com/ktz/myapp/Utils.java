package com.ktz.myapp;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Utils {

    public static boolean isEmpty(TextInputEditText name, TextInputEditText email, TextInputEditText pass) {
        if (name != null && email != null && pass != null) {
            return name.getText().toString().isEmpty() && email.getText().toString().isEmpty();
        }
        return false;
    }

    public static void chByType(String regex, String s, boolean status, CardView cardView) {
        if (s.matches(regex)) {
            status = true;
            cardView.setCardBackgroundColor(Color.WHITE);
        } else {
            status = false;
            cardView.setCardBackgroundColor(Color.RED);
        }
    }


    /**
     * @return true if text contain special character*/
    public static boolean isContainSpecialChar(String s) {
        return s.contains("~") | s.contains("!") | s.contains("@") | s.contains("#") |
                s.contains("$") | s.contains("%") | s.contains("^") | s.contains("&") |
                s.contains("*") | s.contains("(") | s.contains(")") | s.contains("-") |
                s.contains("-") | s.contains("=") | s.contains("+") | s.contains("[") |
                s.contains("]") | s.contains("{") | s.contains("}") | s.contains("\\") |
                s.contains("|") | s.contains(";") | s.contains(":") | s.contains("\"") |
                s.contains("'") | s.contains(",") | s.contains(".") | s.contains("/") |
                s.contains("<") | s.contains(">") | s.contains("?");
    }


    public static void clearErrorText(TextInputLayout layout){
        layout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count>0){
                    layout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
