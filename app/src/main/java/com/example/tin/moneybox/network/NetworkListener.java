package com.example.tin.moneybox.network;

import com.example.tin.moneybox.User;

import java.util.ArrayList;

public interface NetworkListener {

    interface LoginListener {
        void getResponse(ArrayList<User> user);
    }
}
