package com.example.tin.moneybox.utils;


public class EmailValidationUtils {

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

