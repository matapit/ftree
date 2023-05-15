package com.example.famille.controller.ui.logout;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.famille.R;
import com.example.famille.app.App;
import com.example.famille.app.DatabaseManager;
import com.example.famille.controller.LoginActivity;
import com.example.famille.controller.PersonActivity;


public class LogoutFragment extends Fragment {

    private DatabaseManager databaseManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseManager= new DatabaseManager(getContext());
        databaseManager.drop();
        Intent loginActivity = new Intent(getContext(), LoginActivity.class);
        getContext().startActivity(loginActivity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
}