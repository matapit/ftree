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

import com.example.famille.R;
import com.example.famille.app.App;
import com.example.famille.controller.EvenementActivity;
import com.example.famille.controller.EventActivity;
import com.example.famille.modelle.Event;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private ArrayList<Event> events;
    public EventAdapter(ArrayList<Event> events){
        this.events = events;
    }
    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent,false);

        return new EventAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final EventAdapter.ViewHolder holder, int position) {
        Log.d("APP", "ok");

        final Event event = events.get(position);
        holder.itemNameEvent.setText(event.getName());
        holder.itemDate.setText(event.getDate());
        holder.itemCaniote.setText(String.valueOf(event.getCaniote()));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(holder.itemView.getContext(), EvenementActivity.class);
                App.id_event = event.getId();
                holder.itemView.getContext().startActivity(loginActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView itemNameEvent, itemDate, itemCaniote;
        public LinearLayout item;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            Log.d("APP", "ok");
            itemNameEvent = (TextView) itemView.findViewById(R.id.itemNameEvent);
            itemDate = (TextView) itemView.findViewById(R.id.itemDate);
            itemCaniote = itemView.findViewById(R.id.itemCaniote);
            item = itemView.findViewById(R.id.itemEvent);

        }
    }
}
