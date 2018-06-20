package com.example.tin.moneybox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tin.moneybox.models.Product;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements DetailContract.DetailScreen {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private DetailPresenter detailPresenter;

    private ArrayList<Product> mProducts;

    int positionClicked;

    TextView moneyboxTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailPresenter = new DetailPresenter(this);

        moneyboxTv = findViewById(R.id.tV_moneybox);

        Intent getIntent = getIntent();

        if (getIntent != null) {
            mProducts = getIntent.getParcelableArrayListExtra(MainActivity.PRODUCT_LIST);
            positionClicked = getIntent.getIntExtra(MainActivity.POSITION_CLICKED,-1);
            Log.d(TAG, "List: " + mProducts);
            Log.d(TAG, "positionClicked: " + positionClicked);

            detailPresenter.prepareArrayListData(mProducts, positionClicked);

        } else {
            Toast.makeText(this, "Error loading data, please try again.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void populateDetailView(int moneybox, String friendlyName) {

        //moneybox = Integer.parseInt("Your Moneybox: £" + Integer.parseInt(String.valueOf((moneybox))));
        setTitle(friendlyName);
        moneyboxTv.setText(String.valueOf(moneybox));

    }
}
