package com.ktz.myapp;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ktz.myapp.database.DataBaseHelper;

public class Utils {

    // check is the text empty
    public static boolean isEmpty(String name, String email, String pass,String phone,String age,String gender,String country) {
        if (name != null && email != null && pass != null) {
            return name.isEmpty() && email.isEmpty() && pass.isEmpty()&&phone.isEmpty()&&age.isEmpty()&&gender.isEmpty()&&country.isEmpty();
        }
        return false;
    }
    public static boolean isEmpty(String name, String email, String pass) {
        if (name != null && email != null && pass != null) {
            return name.isEmpty() && email.isEmpty() && pass.isEmpty();
        }
        return false;
    }

    /**
     * Check For CardView Effect and give it status
     */
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
     * Check Email Format
     */
    public static boolean isEmailValidation(String s) {
        return s.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }


    /**
     * @return true if text contain special character
     */
    public static boolean isContainSpecialChar(String s) {
        return s.contains("~") | s.contains("!") | s.contains("@") | s.contains("#") |
                s.contains("$") | s.contains("%") | s.contains("^") | s.contains("&") |
                s.contains("*") | s.contains("(") | s.contains(")") | s.contains("_") |
                s.contains("-") | s.contains("=") | s.contains("+") | s.contains("[") |
                s.contains("]") | s.contains("{") | s.contains("}") | s.contains("\\") |
                s.contains("|") | s.contains(";") | s.contains(":") | s.contains("\"") |
                s.contains("'") | s.contains(",") | s.contains(".") | s.contains("/") |
                s.contains("<") | s.contains(">") | s.contains("?");
    }

    /**
     * Clear Error Text on Text change
     */
    public static void clearErrorText(TextInputLayout layout) {
        layout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    layout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static boolean isEmailExist(Context context,String email) {
        DataBaseHelper db = new DataBaseHelper(context);
        for (int i = 1; i <= db.userCount(); i++) {
            if (email.equals(db.getValue(i,DataBaseHelper.KEY_EMAIL))){
                return true;
            }
        }
        return false;
    }
}
