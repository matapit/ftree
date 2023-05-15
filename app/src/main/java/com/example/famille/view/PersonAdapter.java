package com.example.famille.view;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famille.MainActivity;
import com.example.famille.R;
import com.example.famille.app.App;
import com.example.famille.controller.LoginActivity;
import com.example.famille.controller.PersonActivity;
import com.example.famille.modelle.Person;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> persons;
    public PersonAdapter(ArrayList<Person> persons){
        this.persons = persons;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent,false);

        return new PersonAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d("APP", "ok");

        final Person person = persons.get(position);
    holder.itemNom.setText(person.getFirstname());
    holder.itemTel.setText(person.getTel());
    holder.itemGeneration.setText(String.valueOf(person.getGenreration()));
    holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(holder.itemView.getContext(), PersonActivity.class);
                App.id = person.getId();
                holder.itemView.getContext().startActivity(loginActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView itemNom, itemTel, itemGeneration;
        public LinearLayout item;
         public ViewHolder(@NonNull final View itemView) {
            super(itemView);
             Log.d("APP", "ok");
            itemNom = (TextView) itemView.findViewById(R.id.itemName);
            itemTel = (TextView) itemView.findViewById(R.id.itemTel);
            itemGeneration = itemView.findViewById(R.id.itemGeneration);
            item = itemView.findViewById(R.id.item);

    }
}
}
