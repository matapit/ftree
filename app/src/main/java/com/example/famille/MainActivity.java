package com.example.famille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.famille.app.DatabaseManager;
import com.example.famille.controller.HomeActivity;
import com.example.famille.controller.LoginActivity;
import com.example.famille.modelle.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseManager db = new DatabaseManager(this);

 
        if(!User.isConnect(this)){
            Intent loginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginActivity);
        }else {
            Intent loginActivity = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(loginActivity);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}