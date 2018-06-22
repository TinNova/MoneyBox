package com.example.tin.moneybox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tin.moneybox.models.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginScreen {

    /* Key for Intent */
    public static String USER_ARRAY_LIST;

    private LoginPresenter loginPresenter;
    private TextView emailTextView;
    private TextView passTextView;

    private Button loginButton;
    private EditText emailEditText;
    private EditText passEditText;

    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadingIndicator = findViewById(R.id.pB_lgn_loading);
        loginButton = findViewById(R.id.btn_lgn_login);
        emailEditText = findViewById(R.id.eT_lgn_email);
        passEditText = findViewById(R.id.eT_lgn_pass);
        emailTextView = findViewById(R.id.tV_lgn_email);
        passTextView = findViewById(R.id.tV_lgn_pass);

        loginPresenter = new LoginPresenter(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get String from email and pass and send it to the presenter
                String email = emailEditText.getText().toString();
                String pass = passEditText.getText().toString();

                loginPresenter.startLogin(LoginActivity.this, email, pass);

            }
        });
    }

    @Override
    public void launchMainActivity(ArrayList<User> user) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra(USER_ARRAY_LIST, user);

        startActivity(intent);
    }

    @Override
    public void showLoading() {

        loadingIndicator.setVisibility(View.VISIBLE);

        emailTextView.setVisibility(View.INVISIBLE);
        emailEditText.setVisibility(View.INVISIBLE);
        passTextView.setVisibility(View.INVISIBLE);
        passEditText.setVisibility(View.INVISIBLE);
        loginButton.setVisibility(View.INVISIBLE);
    }

}
