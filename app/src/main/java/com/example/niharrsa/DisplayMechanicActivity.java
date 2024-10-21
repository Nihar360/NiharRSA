package com.example.niharrsa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayMechanicActivity extends AppCompatActivity {

    private TextView nameTextView, phoneTextView, vehicleTextView, locationTextView, priceTextView;
    private Button callMechanicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searched_mechanic);

        // Initialize the UI components
        nameTextView = findViewById(R.id.NameTxt);
        phoneTextView = findViewById(R.id.PhoneNumberTxt);
        vehicleTextView = findViewById(R.id.VehicleNameTxt);
        locationTextView = findViewById(R.id.LocationTxt);
        priceTextView = findViewById(R.id.PriceTxt);
        callMechanicButton = findViewById(R.id.CallMechanicBtn);

        // Get the data from the Intent
        Intent intent = getIntent();
        String mechanicName = intent.getStringExtra("mechanicName");
        String mechanicPhone = intent.getStringExtra("mechanicNumber");
        String vehicleName = intent.getStringExtra("vehicleName");
        String mechanicLocation = intent.getStringExtra("mechanicLocation");
        String mechanicPrice = intent.getStringExtra("mechanicPrice");

        // Set the data in the TextViews
        nameTextView.setText("Mechanic Name: " + mechanicName);
        phoneTextView.setText("Phone Number: " + mechanicPhone);
        vehicleTextView.setText("Vehicle Name: " + vehicleName);
        locationTextView.setText("Location: " + mechanicLocation);
        priceTextView.setText("Price: " + mechanicPrice);

        // Set up Call Mechanic Button action
        callMechanicButton.setOnClickListener(v -> {
            // Open the dialer with the mechanic's phone number
            if (mechanicPhone != null && !mechanicPhone.isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + mechanicPhone));
                startActivity(callIntent);
            } else {
                // Handle case where phone number is not available
                Toast.makeText(this, "Phone number is not available.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
