package com.example.niharrsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MechanicAdapter extends RecyclerView.Adapter<MechanicAdapter.ViewHolder> {

    private List<MechanicModel> mechanicList;
    private Context context;

    public MechanicAdapter(List<MechanicModel> mechanicList, Context context) {
        this.mechanicList = mechanicList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mechanic_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MechanicModel mechanic = mechanicList.get(position);
        holder.mechanicNameTextView.setText("Name: " + mechanic.getMechanicName());
        holder.mechanicLocationTextView.setText("Location: " + mechanic.getMechanicLocation());
        holder.mechanicPriceTextView.setText("Price: " + mechanic.getMechanicPrice());
        holder.vehicleNameTextView.setText("Vehicle: " + mechanic.getVehicleName());
    }

    @Override
    public int getItemCount() {
        return mechanicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mechanicNameTextView, mechanicLocationTextView, mechanicPriceTextView, vehicleNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mechanicNameTextView = itemView.findViewById(R.id.mechanic_name);
            mechanicLocationTextView = itemView.findViewById(R.id.mechanic_location);
            mechanicPriceTextView = itemView.findViewById(R.id.mechanic_price);
            vehicleNameTextView = itemView.findViewById(R.id.vehicle_name);
        }
    }
}
