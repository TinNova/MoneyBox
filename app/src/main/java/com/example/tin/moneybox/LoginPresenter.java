package com.example.tin.moneybox;


import android.content.Context;
import android.util.Log;

import java.net.MalformedURLException;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();


    private LoginContract.LoginScreen loginScreen;

    LoginPresenter(LoginContract.LoginScreen screen) {
        this.loginScreen = screen;
    }

    private Context mcontext;

    @Override
    public void startLogin(Context context, String email, String pass) {
        // CODE FOR PASSING USERNAME AND PASSWORD TO SERVER

        Log.d(TAG, "email & password: " + email + ", " + pass);

        String loginUrl = UrlUtils.getLoginUrl();
        // Show Loading Screen

        /* Use the String URL "loginUrl" to request the JSON from the server and parse it */
        NetworkConnection.getInstance(context).getLoginResponseFromHttpUrl(loginUrl, email, pass, new NetworkListener.LoginListener() {
            @Override
            public void getResponse(String string) {

            }
        });
    }

}
