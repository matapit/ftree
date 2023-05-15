package com.example.famille.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.famille.R;
import com.example.famille.app.App;

public class EvenementActivity extends AppCompatActivity {
    private WebView web;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenement);
        back = findViewById(R.id.backEvenement);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivity = new Intent(EvenementActivity.this, HomeActivity.class);
                startActivity(homeActivity);
            }
        });
        web = findViewById(R.id.webEvenement);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://ftreeapi.000webhostapp.com/?page=evenement&event_id="+ App.id_event);
    }
}