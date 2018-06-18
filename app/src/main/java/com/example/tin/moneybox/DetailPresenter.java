package com.example.tin.moneybox;

import android.content.Context;

/**
 * Created by Tin on 18/06/2018.
 */

public class DetailPresenter implements DetailContract.DetailPresenter {

    private DetailContract.DetailScreen detailScreen;

    DetailPresenter(DetailContract.DetailScreen screen) {
        this.detailScreen = screen;
    }

}
