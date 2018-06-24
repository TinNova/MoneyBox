package com.example.tin.moneybox;

import android.util.Log;
import android.widget.Toast;

import com.example.tin.moneybox.models.Product;
import com.example.tin.moneybox.models.User;
import com.example.tin.moneybox.network.NetworkConnection;
import com.example.tin.moneybox.network.NetworkListener;
import com.example.tin.moneybox.utils.UrlUtils;

import java.util.ArrayList;


public class MainPresenter implements MainContract.MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private final MainContract.MainScreen mainScreen;

    MainPresenter(MainContract.MainScreen screen) {
        this.mainScreen = screen;
    }

    @Override
    public void getThisWeekResponse(final MainActivity context, ArrayList<User> user) {

        String thisWeekUrl = UrlUtils.getThisWeekUrl();
        mainScreen.showLoading();

        /* Use the String URL "loginUrl" to request the JSON from the server and parse it */
        NetworkConnection.getInstance(context).getThisWeekResponseFromHttpUrl(thisWeekUrl, user, new NetworkListener.ThisWeekListener() {

            @Override
            public void getResponse(ArrayList<Product> products) {

                if (products == null) {

                    startLogOut(context);
                    Toast.makeText(context, context.getString(R.string.session_timed_out), Toast.LENGTH_SHORT).show();

                } else {

                    Log.d(TAG, "thisWeek Products ArrayList: " + products);
                    mainScreen.showProducts(products);
                }
            }
        });
    }

    @Override
    public void startLogOut(final MainActivity context) {

        String logoutUrl = UrlUtils.getLogoutUrl();

        /* Use the String URL "logoutUrl" to request the JSON from the server and parse it */
        NetworkConnection.getInstance(context).getLogOutResponseFromHttpUrl(logoutUrl, new NetworkListener.LogoutListener() {

            @Override
            public void getResponse() {

                Log.v(TAG, "Logout Successful:");

                mainScreen.logout();
            }
        });
    }
}
