package com.example.tin.moneybox;

import com.example.tin.moneybox.models.Product;
import com.example.tin.moneybox.models.User;

import java.util.ArrayList;


interface MainContract {

    interface MainScreen {

        void showProducts(ArrayList<Product> products);

        void logout();

        void showLoading();

        void hideLoading();
    }

    interface MainPresenter {

        void getThisWeekResponse(MainActivity mainActivity, ArrayList<User> user);

        void startLogOut(MainActivity mainActivity);
    }
}
