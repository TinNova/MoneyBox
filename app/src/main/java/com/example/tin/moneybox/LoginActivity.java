package com.example.tin.moneybox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tin.moneybox.models.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginScreen {

    /* Key for Intent */
    public static String USER_ARRAY_LIST;

    private LoginPresenter loginPresenter;

    private Button loginButton;
    private EditText emailEditText;
    private EditText passEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Initialising all of the buttons */
        loginButton = findViewById(R.id.btn_Login);
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

    @Override
    public void launchMainActivity(ArrayList<User> user) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra(USER_ARRAY_LIST, user);

        startActivity(intent);
    }
}
