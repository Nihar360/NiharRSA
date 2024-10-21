package com.example.niharrsa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class admin3 extends AppCompatActivity {

    private TextView nameTextView, mobileTextView, vehicleTypeTextView, locationTextView;
    private Button searchMechanicsButton, callCustomerButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin3);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize TextViews and Buttons
        nameTextView = findViewById(R.id.tv_name);
        mobileTextView = findViewById(R.id.tv_mobile);
        vehicleTypeTextView = findViewById(R.id.tv_vehicle_type);
        locationTextView = findViewById(R.id.tv_location);
        searchMechanicsButton = findViewById(R.id.btn_search_mechanics);
        callCustomerButton = findViewById(R.id.btn_call_customer); // Fix variable name

        // Set up the call customer button click listener
        callCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = mobileTextView.getText().toString(); // Use mobileTextView for the number
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        // Get data from the Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String mobile = intent.getStringExtra("mobile");
        String vehicleType = intent.getStringExtra("vehicleType");
        String location = intent.getStringExtra("location");
        String requestId = intent.getStringExtra("requestId");

        // Display the data in the TextViews
        nameTextView.setText(" " + name);
        mobileTextView.setText(" " + mobile);
        vehicleTypeTextView.setText(" " + vehicleType);
        locationTextView.setText(" " + location);

        // Set OnClickListener for Search Mechanics Button
        searchMechanicsButton.setOnClickListener(v -> searchMechanics(vehicleType, location, requestId));
    }

    private void searchMechanics(String vehicleName, String location, String requestId) {
        // Validate inputs
        if (vehicleName.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please provide both vehicle type and location.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform a Firestore query to find matching mechanics
        db.collection("mechanics")
                .whereEqualTo("vehicleName", vehicleName.toLowerCase().trim())  // Lowercase for consistency
                .whereEqualTo("mechanicLocation", location.toLowerCase().trim()) // Lowercase for consistency
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Display the first mechanic found or pass the list to a new activity
                        List<DocumentSnapshot> mechanicsList = task.getResult().getDocuments();
                        displayMechanic(mechanicsList.get(0), requestId);
                    } else {
                        Toast.makeText(this, "No mechanics found for the specified criteria.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to retrieve mechanics: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    // Method to display the mechanic details in a new activity
    private void displayMechanic(DocumentSnapshot mechanic, String requestId) {
        // Get mechanic details from Firestore
        String name = mechanic.getString("mechanicName");
        String phone = mechanic.getString("mechanicNumber");
        String vehicleName = mechanic.getString("vehicleName");
        String location = mechanic.getString("mechanicLocation");
        String price = mechanic.getString("mechanicPrice");

        // Create an Intent to navigate to the DisplayMechanicActivity
        Intent intent = new Intent(this, DisplayMechanicActivity.class);
        intent.putExtra("mechanicName", name);
        intent.putExtra("mechanicNumber", phone);
        intent.putExtra("vehicleName", vehicleName);
        intent.putExtra("mechanicLocation", location);
        intent.putExtra("mechanicPrice", price);
        intent.putExtra("userId", requestId); // Pass requestId if needed for reference

        // Start the new activity to display mechanic details
        startActivity(intent);
    }
}
