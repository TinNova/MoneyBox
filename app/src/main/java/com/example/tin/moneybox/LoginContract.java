package com.example.tin.moneybox;

import android.content.Context;

/**
 * Created by Tin on 16/06/2018.
 */

public interface LoginContract {

    interface LoginScreen {
    }

    interface LoginPresenter {

        void startLogin(Context context, String email, String pass);

    }
}
