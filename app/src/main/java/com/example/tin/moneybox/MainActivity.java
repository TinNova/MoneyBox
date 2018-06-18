package com.example.tin.moneybox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tin.moneybox.adapters.ProductAdapter;
import com.example.tin.moneybox.models.Product;
import com.example.tin.moneybox.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainScreen, ProductPositionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainPresenter mainPresenter;

    ArrayList<User> mUser;

    String firstName;
    String lastName;
    String title;

    private Button logOutButton;

    /*
     * Needed to populate the Adapter and the RecyclerView
     */
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    /* Used for savedInstanceState */
    private ArrayList<Product> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);

        logOutButton = findViewById(R.id.btn_Logout);

        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_productList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ProductAdapter(null, getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        Intent getIntent = getIntent();

        if (getIntent != null) {
            mUser = getIntent.getParcelableArrayListExtra(LoginActivity.USER_ARRAY_LIST);

            firstName = mUser.get(0).getUserFirstName();
            lastName = mUser.get(0).getUserLastName();
            title = "Welcome, " + " " + firstName + " " + lastName;

            setTitle(title);

            mainPresenter.getThisWeekResponse(MainActivity.this, mUser);

        } else {
            Toast.makeText(this, "Error loading data, please try again.", Toast.LENGTH_SHORT).show();
        }

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPresenter.startLogOut(MainActivity.this);
            }
        });

        Log.d(TAG, "mUser: " + mUser);

    }

    @Override
    public void showProducts(ArrayList<Product> products) {
        mProducts = products;
        mAdapter.setProducts(products);
        //hideLoading();
    }

    @Override
    public void logout() {

        //TODO: Delete username, password and saved token
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void btnProductClick(View v, int position) {

        Log.d(TAG, "Item Position: " + position);

        Toast.makeText(this, "Clicked Position " + position, Toast.LENGTH_SHORT).show();

    }
}
