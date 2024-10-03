package com.example.niharrsa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddMechanicActivity extends AppCompatActivity {

    private EditText mechanicNameEditTxt, mechanicNumberEditTxt, mechanicLocationEditTxt, mechanicPriceEditTxt, vehicleNameEditTxt;
    private Button addMechanicDetailsBtn;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the appropriate layout
        setContentView(R.layout.activity_add_mechanic);

        // Initialize Firebase components
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        mechanicNameEditTxt = findViewById(R.id.MechanicNameEditTxt);
        mechanicNumberEditTxt = findViewById(R.id.MechanicNumberEditTxt);
        mechanicLocationEditTxt = findViewById(R.id.LocationEditTxt);
        mechanicPriceEditTxt = findViewById(R.id.PriceEditTxt);
        vehicleNameEditTxt = findViewById(R.id.VehicleNameEditTxt);
        addMechanicDetailsBtn = findViewById(R.id.AddNotificationBtn);
        progressBar = findViewById(R.id.ProgressBar); // Initialize ProgressBar

        // Set onClickListener for the button
        addMechanicDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMechanicInfo();
            }
        });
    }

    private void saveMechanicInfo() {
        String mechanicName = mechanicNameEditTxt.getText().toString().trim();
        String mechanicNumber = mechanicNumberEditTxt.getText().toString().trim();
        String mechanicLocation = mechanicLocationEditTxt.getText().toString().trim();
        String mechanicPrice = mechanicPriceEditTxt.getText().toString().trim();
        String vehicleName = vehicleNameEditTxt.getText().toString().trim();

        // Validate input fields
        if (!validateFields(mechanicName, mechanicNumber, mechanicLocation, mechanicPrice, vehicleName)) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE); // Show loading indicator
        addMechanicToDatabase(mechanicName, mechanicNumber, mechanicLocation, mechanicPrice, vehicleName);
    }

    private boolean validateFields(String mechanicName, String mechanicNumber, String mechanicLocation, String mechanicPrice, String vehicleName) {
        if (mechanicName.isEmpty()) {
            mechanicNameEditTxt.setError("Mechanic name is required.");
            mechanicNameEditTxt.requestFocus();
            return false;
        }

        if (mechanicNumber.isEmpty()) {
            mechanicNumberEditTxt.setError("Mechanic number is required.");
            mechanicNumberEditTxt.requestFocus();
            return false;
        }

        if (mechanicLocation.isEmpty()) {
            mechanicLocationEditTxt.setError("Location is required.");
            mechanicLocationEditTxt.requestFocus();
            return false;
        }

        if (mechanicPrice.isEmpty()) {
            mechanicPriceEditTxt.setError("Price is required.");
            mechanicPriceEditTxt.requestFocus();
            return false;
        }

        if (vehicleName.isEmpty()) {
            vehicleNameEditTxt.setError("Vehicle name is required.");
            vehicleNameEditTxt.requestFocus();
            return false;
        }

        return true;
    }

    private void addMechanicToDatabase(String mechanicName, String mechanicNumber, String mechanicLocation, String mechanicPrice, String vehicleName) {
        // Get the current authenticated user's ID
        String uid = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : null;

        if (uid == null) {
            Toast.makeText(AddMechanicActivity.this, "User not authenticated. Please log in.", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE); // Hide loading indicator
            return;
        }

        // Create a HashMap to store mechanic details
        Map<String, Object> mechanicDetails = new HashMap<>();
        mechanicDetails.put("mechanicName", mechanicName);
        mechanicDetails.put("mechanicNumber", mechanicNumber);
        mechanicDetails.put("mechanicLocation", mechanicLocation);
        mechanicDetails.put("mechanicPrice", mechanicPrice);
        mechanicDetails.put("vehicleName", vehicleName);
        mechanicDetails.put("userId", uid); // Link to the user who created it

        // Generate a unique document ID for each mechanic
        DocumentReference mechanicRef = db.collection("mechanics").document();

        // Store the mechanic details in Firestore
        mechanicRef.set(mechanicDetails)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE); // Hide loading indicator
                        if (task.isSuccessful()) {
                            clearInputFields();
                            Toast.makeText(AddMechanicActivity.this, "Mechanic details added successfully.", Toast.LENGTH_SHORT).show();

//                         Intent intent = new Intent(AddMechanicActivity.this, MechanicSRListActivity.class);
//                          startActivity(intent);
//                            finish(); // Optionally close the current activity
                        } else {
                            Toast.makeText(AddMechanicActivity.this, "Failed to add mechanic details. Try again.", Toast.LENGTH_SHORT).show();
                            Log.e("AddMechanicActivity", "Error adding mechanic details: ", task.getException());
                        }
                    }
                });
    }

    private void clearInputFields() {
        mechanicNameEditTxt.setText("");
        mechanicNumberEditTxt.setText("");
        mechanicLocationEditTxt.setText("");
        mechanicPriceEditTxt.setText("");
        vehicleNameEditTxt.setText("");
    }
}
