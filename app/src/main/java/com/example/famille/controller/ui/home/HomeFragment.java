package com.example.famille.controller.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famille.R;
import com.example.famille.app.App;
import com.example.famille.modelle.Person;
import com.example.famille.view.PersonAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static ArrayList<Person> persons ;
    RecyclerView rv_home;
    PersonAdapter personAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        persons = new ArrayList<>();

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rv_home = (RecyclerView) root.findViewById(R.id.rv_home);
        getPerson();




        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    public void getPerson(){

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .build();


        Request myGetRequest = new Request.Builder()
                .url(App.url+"/person")
                .get()
                .build();

        Log.d("APP", String.valueOf(App.url+"/person"));
        okHttpClient.newCall(myGetRequest).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //le retour est effectué dans un thread différent
                final String texte = response.body().string();
                final int statusCode = response.code();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("APP", "oki");
                        try {
                            JSONArray json = new JSONArray(texte);
                            Log.d("APP", json.getJSONObject(0).toString());

                            for (int i = 0; i < json.length(); i++) {
                                JSONObject jsonPerson =  json.getJSONObject( i);
                                //Log.d("APP", jsonPerson.getString("first_name"));

                                persons.add(new Person(jsonPerson.getInt("id"),jsonPerson.getString("first_name"), jsonPerson.getString("last_name"),jsonPerson.getString("born_on"),jsonPerson.getString("gender"),jsonPerson.getString("tel"),
                                        jsonPerson.getString("employ"),jsonPerson.getString("country"),jsonPerson.getString("city"),jsonPerson.getString("profession"),jsonPerson.getString("account_state"),jsonPerson.getInt("generation"),
                                        jsonPerson.getString("parent_id")  == "null" ? 0 : jsonPerson.getInt("parent_id"),jsonPerson.getString("created_at"),jsonPerson.getString("updated_at"),jsonPerson.getString("email"),jsonPerson.getString("password"),jsonPerson.getString("matrimonial"),jsonPerson.getString("alive")));
                                Log.d("APP", HomeFragment.persons.toString());


                            }

                            personAdapter = new PersonAdapter(persons);
                            rv_home.setAdapter(personAdapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



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
