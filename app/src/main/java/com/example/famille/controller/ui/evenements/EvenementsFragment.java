package com.example.famille.controller.ui.evenements;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.famille.R;
import com.example.famille.app.App;
import com.example.famille.controller.ui.ascendants.AscendantsFragment;
import com.example.famille.controller.ui.ascendants.AscendantsViewModel;
import com.example.famille.modelle.Event;
import com.example.famille.modelle.Person;
import com.example.famille.view.EventAdapter;
import com.example.famille.view.PersonAdapter;

import org.jetbrains.annotations.NotNull;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EvenementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EvenementsFragment extends Fragment {


    private AscendantsViewModel mViewModel;
    public static ArrayList<Event> events ;
    RecyclerView rv;
    EventAdapter eventAdapter;
    public static AscendantsFragment newInstance() {
        return new AscendantsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        events = new ArrayList<>();

        View root = inflater.inflate(R.layout.fragment_evenements, container, false);
        rv = (RecyclerView) root.findViewById(R.id.rv_evenements);
        getPerson();




        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;


    }



    public void getPerson(){

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .build();


        Request myGetRequest = new Request.Builder()
                .url(App.url+"?page=events")
                .post(formBody)
                .build();

        Log.d("APP", String.valueOf(App.url+"?page=events"));
        okHttpClient.newCall(myGetRequest).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //le retour est effectué dans un thread différent
                final String texte = response.body().string();
                final int statusCode = response.code();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("APP", "ok");
                        Log.d("APP", texte);
                        try {
                            JSONObject json = new JSONObject(texte);
                            Log.d("APP", texte);
                            int size = json.getInt("size");
                            for (int i = 0; i < size; i++) {
                                JSONObject jsonEvent =  new JSONObject(json.getString("event"+i));
                                Log.d("APP", texte);
                                events.add(new Event(jsonEvent.getInt("id"), jsonEvent.getString("name"), jsonEvent.getString("date"), jsonEvent.getString("description"), jsonEvent.getInt("canniote")));
                                Log.d("APP", jsonEvent.getString("name"));



                            }
                            Log.d("APP", String.valueOf(events.size()));
                            eventAdapter = new EventAdapter(events);
                            rv.setAdapter(eventAdapter);


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