package com.example.tin.moneybox;

import android.util.Log;
import android.widget.Toast;

import com.example.tin.moneybox.models.Product;
import com.example.tin.moneybox.network.NetworkConnection;
import com.example.tin.moneybox.network.NetworkListener;
import com.example.tin.moneybox.utils.UrlUtils;

import java.util.ArrayList;


public class DetailPresenter implements DetailContract.DetailPresenter {

    private static final String TAG = DetailPresenter.class.getSimpleName();

    private final int PAYMENT_ERROR = -1;

    private final DetailContract.DetailScreen detailScreen;

    DetailPresenter(DetailContract.DetailScreen screen) {
        this.detailScreen = screen;
    }

    private int investorProductId;

    @Override
    public void prepareArrayListData(ArrayList<Product> mProducts, int position) {

        //Product product = mProducts.get(position);

        int moneybox = mProducts.get(position).getMoneybox();
        String productFriendlyName = mProducts.get(position).getFriendlyName();
        investorProductId = mProducts.get(position).getInvestorProductId();

        detailScreen.populateDetailView(moneybox, productFriendlyName);

    }

    @Override
    public void depositMoney(final DetailActivity context) {

        String oneOffPaymentsUrl = UrlUtils.getOneOffPaymentsUrl();

        /* Use the String URL "loginUrl" to request the JSON from the server and parse it */
        NetworkConnection.getInstance(context).getOneOffPaymentsResponseFromHttpUrl(oneOffPaymentsUrl, investorProductId, new NetworkListener.OneOffPaymentListener() {

            @Override
            public void getResponse(int amountInMoneybox) {

                if (amountInMoneybox == PAYMENT_ERROR) {
                    startLogOut(context);

                } else {
                    detailScreen.updateMoneyBox(amountInMoneybox);
                }
            }
        });

    }

    @Override
    public void startLogOut(final DetailActivity context) {

        String logoutUrl = UrlUtils.getLogoutUrl();

        /* Use the String URL "logoutUrl" to request the JSON from the server and parse it */
        NetworkConnection.getInstance(context).getLogOutResponseFromHttpUrl(logoutUrl, new NetworkListener.LogoutListener() {

            @Override
            public void getResponse() {

                Log.v(TAG, "Logout Successful:");
                Toast.makeText(context, context.getString(R.string.session_timed_out), Toast.LENGTH_SHORT).show();

                detailScreen.logout();
            }
        });
    }
}
