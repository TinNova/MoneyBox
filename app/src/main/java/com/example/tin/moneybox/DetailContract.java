package com.example.tin.moneybox;

import com.example.tin.moneybox.models.Product;

import java.util.ArrayList;


interface DetailContract {

    interface DetailScreen {

        void populateDetailView(int moneybox, String friendlyName);

        void updateMoneyBox(int moneybox);

        void logout();
    }

    interface DetailPresenter {

        void prepareArrayListData(ArrayList<Product> mProducts, int position);

        void depositMoney(DetailActivity context);

        void startLogOut(DetailActivity context);

    }
}
