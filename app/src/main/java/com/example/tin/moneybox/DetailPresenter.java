package com.example.tin.moneybox;

import com.example.tin.moneybox.models.Product;

import java.util.ArrayList;


public class DetailPresenter implements DetailContract.DetailPresenter {

    private DetailContract.DetailScreen detailScreen;

    DetailPresenter(DetailContract.DetailScreen screen) {
        this.detailScreen = screen;
    }

    @Override
    public void prepareArrayListData(ArrayList<Product> mProducts, int position) {

        Product product = mProducts.get(position);

        int moneybox = product.getMoneybox();
        String productFriendlyName = product.getFriendlyName();

        detailScreen.populateDetailView(moneybox, productFriendlyName);
    }
}
