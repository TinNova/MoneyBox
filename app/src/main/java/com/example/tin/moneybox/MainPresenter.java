package com.example.tin.moneybox;

import android.util.Log;

import com.example.tin.moneybox.models.Product;
import com.example.tin.moneybox.models.User;
import com.example.tin.moneybox.network.NetworkConnection;
import com.example.tin.moneybox.network.NetworkListener;
import com.example.tin.moneybox.utils.UrlUtils;

import java.util.ArrayList;


public class MainPresenter implements MainContract.MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainContract.MainScreen mainScreen;

    MainPresenter(MainContract.MainScreen screen) {
        this.mainScreen = screen;
    }

    @Override
    public void getThisWeekResponse(MainActivity context, ArrayList<User> user) {

        String thisWeekUrl = UrlUtils.thisWeekUrl();

        /* Use the String URL "loginUrl" to request the JSON from the server and parse it */
        NetworkConnection.getInstance(context).getThisWeekResponseFromHttpUrl(thisWeekUrl, user, new NetworkListener.ThisWeekListener() {

            @Override
            public void getResponse(ArrayList<Product> products) {

                Log.d(TAG, "thisWeek Products ArrayList: " + products);
                Log.d(TAG, "thisWeek Products ArrayList: " + products);

                mainScreen.showProducts(products);
            }
        });

    }
}
