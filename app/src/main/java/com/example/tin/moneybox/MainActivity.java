package com.example.tin.moneybox;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tin.moneybox.adapters.ProductAdapter;
import com.example.tin.moneybox.models.Product;
import com.example.tin.moneybox.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainScreen, ProductPositionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    /* Key for Intent */
    public static final String PRODUCT_LIST = "product_list";
    public static final String POSITION_CLICKED = "positionClicked";

    private final String SAVED_INSTANT_STATE_KEY = "save_instant_state_key";


    private MainPresenter mainPresenter;

    private String firstName;
    private String lastName;
    private String title;

    private Button logOutButton;
    private TextView titleTextView;
    private ProgressBar loadingIndicator;

    /*
     * Needed to populate the Adapter and the RecyclerView
     */
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    /* Used for savedInstanceState */
    private ArrayList<Product> mProducts;
    private ArrayList<User> mUser;

    private boolean DETAIL_ACTIVITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MAIN ACTIVITY onCreate");

        mainPresenter = new MainPresenter(this);

        logOutButton = findViewById(R.id.btn_main_Logout);
        titleTextView = findViewById(R.id.tV_main_title);
        loadingIndicator = findViewById(R.id.pB_main_loading);

        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_main_productList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ProductAdapter(null, getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        DETAIL_ACTIVITY = false;

        /* If There isn't a savedInstanceState, Download The Data And Build The RecyclerView */
        if (savedInstanceState != null) {
            mProducts = savedInstanceState.getParcelableArrayList(SAVED_INSTANT_STATE_KEY);
            showProducts(mProducts);
        } else {

            Intent getIntent = getIntent();

            if (getIntent != null) {
                mUser = getIntent.getParcelableArrayListExtra(LoginActivity.USER_ARRAY_LIST);

                firstName = mUser.get(0).getUserFirstName();
                lastName = mUser.get(0).getUserLastName();

                title = this.getString(R.string.welcome_back) + firstName;

                setTitle(title);

                mainPresenter.getThisWeekResponse(MainActivity.this, mUser);

            } else {
                title = this.getString(R.string.welcome_back) + firstName;
            }
        }

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPresenter.startLogOut(MainActivity.this);
            }
        });

    }

    @Override
    public void showProducts(ArrayList<Product> products) {
        Log.d(TAG, "Products: " + products);
        mProducts = products;
        mAdapter.setProducts(products);
        hideLoading();
    }

    @Override
    public void logout() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void btnProductClick(View v, int position) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(POSITION_CLICKED, position);
        intent.putParcelableArrayListExtra(PRODUCT_LIST, mProducts);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        /* Make pop-up appear prompting user that they are about to logout */
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_logout, null);
        Button logoutButton = dialogView.findViewById(R.id.btn_dialogout_logout);
        Button cancelButton = dialogView.findViewById(R.id.btn_dialogout_cancel);

        mBuilder.setView(dialogView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.startLogOut(MainActivity.this);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

    }

    @Override
    public void showLoading() {

        loadingIndicator.setVisibility(View.VISIBLE);
        logOutButton.setVisibility(View.INVISIBLE);
        titleTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {

        loadingIndicator.setVisibility(View.INVISIBLE);
        logOutButton.setVisibility(View.VISIBLE);
        titleTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (DETAIL_ACTIVITY) {
        /* Called when user returns to MainActivity from DetailActivity, it ensures data is updated */
            mainPresenter.getThisWeekResponse(MainActivity.this, mUser);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        DETAIL_ACTIVITY = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /* Saving mWeather to be reused should the device rotate */
        outState.putParcelableArrayList(SAVED_INSTANT_STATE_KEY, mProducts);
    }
}
