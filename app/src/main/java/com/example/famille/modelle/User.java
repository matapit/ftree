package com.example.famille.modelle;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.famille.app.DatabaseManager;
import com.example.famille.controller.HomeActivity;
import com.example.famille.controller.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private int id;

    private String name;
    private  String Password;
    public static User user;
    private static DatabaseManager db;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        Password = password;
    }

    public static boolean isConnect(Context context) {

        db = new DatabaseManager( context );

        user = db.getUser();
        if (user != null){
            return true;
        }else {
            return false;
        }
    }
    public static void connect(Context context, User use){
        try {

            db = new DatabaseManager( context );
            Log.d("APP", "oki");
            db.insertUser(use.getName(), use.getPassword());
            user = use;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
