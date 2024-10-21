package com.example.niharrsa;

import android.content.Context;
import android.content.Intent;  // Import for Intent
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private Context context;
    private List<RequestModel> requestList;
    private FirebaseFirestore db;  // Firestore instance for database operations

    // Constructor for the adapter
    public RequestAdapter(Context context, List<RequestModel> requestList) {
        this.context = context;
        this.requestList = requestList;
        this.db = FirebaseFirestore.getInstance();  // Initialize Firestore
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the request card layout
        View view = LayoutInflater.from(context).inflate(R.layout.request_card, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        // Bind data to the request card
        RequestModel request = requestList.get(position);

        // Set the data from the model to the TextViews
        holder.tvName.setText("Name: " + request.getName());
        holder.tvMobile.setText("Mobile: " + request.getMobile());
        holder.tvVehicleType.setText("Vehicle Type: " + request.getVehicleType());
        holder.tvLocation.setText("Location: " + request.getLocation());
        holder.tvDate.setText("Date: " + request.getDate());  // Set the date

        // Log the request details to ensure they are not null
        Log.d("RequestAdapter", "Request Data: " + request.toString());

        // Handle Accept button click
        holder.btnAccept.setOnClickListener(v -> {
            // Update the status to "Accepted" locally and in Firestore
            request.setStatus("Accepted");

            // Check if request ID is not null
            if (request.getId() == null) {
                Toast.makeText(context, "Request ID is null, cannot proceed.", Toast.LENGTH_SHORT).show();
                return;
            }

            db.collection("requests").document(request.getId())
                    .update("status", "Accepted")
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Request Accepted", Toast.LENGTH_SHORT).show();

                        // Create an Intent to navigate to admin3
                        Intent intent = new Intent(context, admin3.class);

                        // Pass relevant data to the next activity
                        intent.putExtra("name", request.getName());
                        intent.putExtra("mobile", request.getMobile());
                        intent.putExtra("vehicleType", request.getVehicleType());
                        intent.putExtra("location", request.getLocation());
                        intent.putExtra("requestId", request.getId());  // Pass request ID for reference

                        // Start the new activity
                        context.startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        holder.btnReject.setOnClickListener(v -> {
            request.setStatus("Rejected");
            db.collection("requests").document(request.getId())
                    .update("status", "Rejected")
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Request Rejected", Toast.LENGTH_SHORT).show();

                        // Redirect to the new CallCustomerActivity with the request details
                        Intent intent = new Intent(context, CallCustomerActivity.class);
                        intent.putExtra("name", request.getName());
                        intent.putExtra("mobile", request.getMobile());
                        intent.putExtra("vehicleType", request.getVehicleType());
                        intent.putExtra("location", request.getLocation());
                        intent.putExtra("date", request.getDate());
                        context.startActivity(intent);
                    })
                    .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    // Define the ViewHolder for the RecyclerView
    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMobile, tvVehicleType, tvLocation, tvDate;  // Add tvDate
        Button btnAccept, btnReject;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMobile = itemView.findViewById(R.id.tv_mobile);
            tvVehicleType = itemView.findViewById(R.id.tv_vehicle_type);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvDate = itemView.findViewById(R.id.tv_date);  // Initialize the date TextView

            btnAccept = itemView.findViewById(R.id.btn_accept);
            btnReject = itemView.findViewById(R.id.btn_reject);
        }
    }
}
