package com.example.tin.moneybox;

import android.content.Context;

import com.example.tin.moneybox.models.User;

import java.util.ArrayList;


public interface LoginContract {

    interface LoginScreen {

        void launchMainActivity(ArrayList<User> user);

        void showLoading();
    }


    interface LoginPresenter {

        void startLogin(Context context, String email, String pass);

    }
}
