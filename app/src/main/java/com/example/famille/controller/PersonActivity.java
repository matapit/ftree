package com.example.famille.controller;

import android.content.Intent;
import android.os.Bundle;

import com.example.famille.app.App;
import com.example.famille.modelle.Person;
import com.example.famille.view.PersonAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.famille.R;

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

public class PersonActivity extends AppCompatActivity {

    private  TextView firstname,lastname,born_on,sexe,tel,employ,country,city,proffesion,genreration,email,matrimonial,generation;
    private Button parent;
    private ImageView back;
    public static ArrayList<Person> persons ;
    RecyclerView rv_person;
    PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivity = new Intent(PersonActivity.this, HomeActivity.class);
                startActivity(homeActivity);
            }
        });

        //gestion du contenue
        firstname =  findViewById(R.id.firstname);
        lastname =  findViewById(R.id.lastname);
        born_on =  findViewById(R.id.born_on);
        sexe =  findViewById(R.id.sexe);
        tel =  findViewById(R.id.tel);
        employ =  findViewById(R.id.employ);
        country =  findViewById(R.id.country);
        city =  findViewById(R.id.city);
        proffesion =  findViewById(R.id.proffesion);

        email =  findViewById(R.id.email);
        matrimonial =  findViewById(R.id.matrimonial);
        generation =  findViewById(R.id.genreration);
        parent = findViewById(R.id.parent);

        getPerson();

        persons = new ArrayList<>();
        rv_person = (RecyclerView) findViewById(R.id.rv_person);
        getChild();
        rv_person.setLayoutManager(new LinearLayoutManager(this));
    }








    public void getPerson(){

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(App.id))
                .build();


        Request myGetRequest = new Request.Builder()
                .url(App.url+"/person/"+App.id)
                .get()
                .build();

        Log.d("APP", String.valueOf(App.url+"/person/"+App.id));
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
                        Log.d("APP", texte);
                        try {

                            JSONObject jsonPerson = new JSONObject(texte);




                               //jsonPerson.getInt("id"),
                                firstname.setText(jsonPerson.getString("first_name"));
                                lastname.setText(jsonPerson.getString("last_name"));
                                born_on.setText(jsonPerson.getString("born_on"));
                                sexe.setText(jsonPerson.getString("gender"));
                                tel.setText(jsonPerson.getString("tel"));
                                employ.setText(jsonPerson.getString("employ"));
                                country.setText(jsonPerson.getString("country"));
                                city.setText(jsonPerson.getString("city"));
                                proffesion.setText(jsonPerson.getString("profession"));
                                generation.setText(jsonPerson.getString("generation"));
                                email.setText(jsonPerson.getString("email"));
                                matrimonial.setText(jsonPerson.getString("matrimonial"));
                                if (jsonPerson.getString("parent_id") != "null") {
                                    setPar(jsonPerson.getInt("parent_id"));
                                }else {
                                    parent.setText("VOIR L'ABRE");
                                }



                            } catch (JSONException ex) {
                            ex.printStackTrace();
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

    public void setPar(int id){

        OkHttpClient okHttpClient = new OkHttpClient();



        Request myGetRequest = new Request.Builder()
                .url(App.url+"/person/"+App.id)
                .get()
                .build();

        Log.d("APP", String.valueOf(App.url+"/person/"+App.id));
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
                        Log.d("APP", texte);
                        try {

                            final JSONObject jsonPerson = new JSONObject(texte);




                               //jsonPerson.getInt("id"),
                            firstname.setText(jsonPerson.getString("first_name"));

                                parent.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        App.id = jsonPerson.getInt("id");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Intent loginActivity = new Intent(PersonActivity.this, PersonActivity.class);

                                    startActivity(loginActivity);
                                }
                            });


                            } catch (JSONException ex) {
                            ex.printStackTrace();
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

    public void getChild(){

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .build();


        Request myGetRequest = new Request.Builder()
                .url(App.url+"/person/children/"+App.id)
                .get()
                .build();

        Log.d("APP", String.valueOf(App.url+"/person/children/"+App.id));
        okHttpClient.newCall(myGetRequest).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //le retour est effectué dans un thread différent
                final String texte = response.body().string();
                final int statusCode = response.code();

                runOnUiThread(new Runnable() {
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
                                Log.d("APP", persons.toString());


                            }

                            personAdapter = new PersonAdapter(persons);
                            rv_person.setAdapter(personAdapter);



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