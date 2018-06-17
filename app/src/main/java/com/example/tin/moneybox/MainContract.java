package com.example.tin.moneybox;

import com.example.tin.moneybox.models.User;

import java.util.ArrayList;

/**
 * Created by Tin on 17/06/2018.
 */

public interface MainContract {

    interface MainScreen {
    }

    interface MainPresenter {

        void getThisWeekResponse(MainActivity mainActivity, ArrayList<User> user);
    }
}
