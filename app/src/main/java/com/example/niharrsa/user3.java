package com.example.niharrsa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class user3 extends AppCompatActivity {

    private EditText etName, etMobile, etVehicleType, etLocation;
    private Button btnRequest;

    // Firestore instance
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user3);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Find the views
        etName = findViewById(R.id.et_name);
        etMobile = findViewById(R.id.et_mobile);
        etVehicleType = findViewById(R.id.et_vehicle_type);
        etLocation = findViewById(R.id.et_location);
        btnRequest = findViewById(R.id.btn_request);

        // Set click listener for the button
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String name = etName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String vehicleType = etVehicleType.getText().toString().trim();
                String location = etLocation.getText().toString().trim();

                // Create a map to store the data
                Map<String, Object> requestData = new HashMap<>();
                requestData.put("name", name);
                requestData.put("mobile", mobile);
                requestData.put("vehicleType", vehicleType);
                requestData.put("location", location);

                // Save data to Firestore
                db.collection("requests")
                        .add(requestData)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(user3.this, "Request Sent Successfully", Toast.LENGTH_SHORT).show();

                            // Redirect to xyz activity after successful submission
                            Intent intent = new Intent(user3.this, user4.class);
                            startActivity(intent);
                            finish();  // Optional: Finish the current activity
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(user3.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
