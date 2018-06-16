package com.example.tin.moneybox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.MalformedURLException;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginScreen {

    private LoginPresenter loginPresenter;

    private Button loginButton;
    private EditText emailEditText;
    private EditText passEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Initialising all of the buttons */
        loginButton = findViewById(R.id.loginButton);
        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passEditText);

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
}
