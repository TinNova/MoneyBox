package com.example.tin.moneybox;


import android.content.Context;
import android.util.Log;

import com.example.tin.moneybox.models.User;
import com.example.tin.moneybox.network.NetworkConnection;
import com.example.tin.moneybox.network.NetworkListener;
import com.example.tin.moneybox.utils.UrlUtils;

import java.util.ArrayList;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();


    private LoginContract.LoginScreen loginScreen;

    LoginPresenter(LoginContract.LoginScreen screen) {
        this.loginScreen = screen;
    }

    private Context mcontext;

    @Override
    public void startLogin(Context context, String email, String pass) {

        Log.d(TAG, "email & password: " + email + ", " + pass);

        String loginUrl = UrlUtils.getLoginUrl();
        // Show Loading Screen

        /* Use the String URL "loginUrl" to request the JSON from the server and parse it */
        NetworkConnection.getInstance(context).getLoginResponseFromHttpUrl(loginUrl, email, pass, new NetworkListener.LoginListener() {

            @Override
            public void getResponse(ArrayList<User> user) {

                loginScreen.launchMainActivity(user);

            }
        });
    }

}
