package com.example.tin.moneybox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tin.moneybox.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainScreen {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainPresenter mainPresenter;

    ArrayList<User> mUser;

    String firstName;
    String lastName;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);

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

        Log.d(TAG, "mUser: " + mUser);

    }
}
