package com.example.famille.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.famille.R;
import com.example.famille.app.App;
import com.example.famille.modelle.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView text = null;
    private EditText email = null;
    private EditText password = null;
    private Button log = null;
    private TextView regist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("APP", String.valueOf("ddddd"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        log = (Button)findViewById(R.id.connect);
        text = (TextView) findViewById(R.id.textView);
        regist = (TextView) findViewById(R.id.regist);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login();
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent RegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(RegisterActivity);
            }
        });


    }


    public void login(){

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("email", email.getText().toString())
                .add("password", password.getText().toString())
                .build();


        Request myGetRequest = new Request.Builder()
                .url(App.url+"/person/auth")
                .post(formBody)
                .build();

        Log.d("APP", String.valueOf(App.url+"/person/auth"));
        okHttpClient.newCall(myGetRequest).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //le retour est effectué dans un thread différent
                final String texte = response.body().string();
                final int statusCode = response.code();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("APP", "ok");
                        try {


                            if (texte != "false"){
                                JSONObject json = new JSONObject(texte);
                                JSONObject user = new JSONObject(texte);


                                Log.d("APP", texte);

                                User.connect(LoginActivity.this, new User(user.getInt("id"), user.getString("email"), user.getString("password")));
                                Intent HomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(HomeActivity);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //json.getBoolean("connect")


                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("APP", "echec");
            }



        });
    }
}