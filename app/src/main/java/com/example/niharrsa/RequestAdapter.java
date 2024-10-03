package com.example.niharrsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<RequestModel> requestList;
    private Context context;
    private FirebaseFirestore db;

    public RequestAdapter(List<RequestModel> requestList, Context context) {
        this.requestList = requestList;
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestModel request = requestList.get(position);

        holder.nameTextView.setText("Name: " + request.getName());
        holder.mobileTextView.setText("Mobile: " + request.getMobile());
        holder.vehicleTypeTextView.setText("Vehicle Type: " + request.getVehicleType());
        holder.locationTextView.setText("Location: " + request.getLocation());

        holder.acceptButton.setOnClickListener(v -> {
            db.collection("requests").document(request.getId())
                    .update("status", "Accepted")
                    .addOnSuccessListener(aVoid -> {
                        // Create an Intent to go to admin3 activity
                        Intent intent = new Intent(context, admin3.class);

                        // Pass request data to admin3 using Intent extras
                        intent.putExtra("name", request.getName());
                        intent.putExtra("mobile", request.getMobile());
                        intent.putExtra("vehicleType", request.getVehicleType());
                        intent.putExtra("location", request.getLocation());
                        intent.putExtra("requestId", request.getId());

                        context.startActivity(intent);
                        Toast.makeText(context, "Request Accepted", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });



        // Reject button
        holder.rejectButton.setOnClickListener(v -> {
            db.collection("requests").document(request.getId())
                    .update("status", "Rejected")
                    .addOnSuccessListener(aVoid -> {
                        requestList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Request Rejected", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView, mobileTextView, vehicleTypeTextView, locationTextView;
        private Button acceptButton, rejectButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            mobileTextView = itemView.findViewById(R.id.tv_mobile);
            vehicleTypeTextView = itemView.findViewById(R.id.tv_vehicle_type);
            locationTextView = itemView.findViewById(R.id.tv_location);
            acceptButton = itemView.findViewById(R.id.btn_accept);
            rejectButton = itemView.findViewById(R.id.btn_reject);
        }
    }
}
