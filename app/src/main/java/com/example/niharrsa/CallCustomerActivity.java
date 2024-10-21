package com.example.niharrsa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CallCustomerActivity extends AppCompatActivity {

    private TextView tvName, tvPhoneNumber, tvVehicleName, tvLocation, tvDate;
    private Button callCustomerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_customer);

        // Initialize views
        tvName = findViewById(R.id.NameTxt);
        tvPhoneNumber = findViewById(R.id.PhoneNumberTxt);
        tvVehicleName = findViewById(R.id.VehicleNameTxt);
        tvLocation = findViewById(R.id.LocationTxt);
        tvDate = findViewById(R.id.DateTxt);
        callCustomerButton = findViewById(R.id.CallCustomerBtn);

        // Get intent data
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String mobile = intent.getStringExtra("mobile");
        String vehicleType = intent.getStringExtra("vehicleType");
        String location = intent.getStringExtra("location");
        String date = intent.getStringExtra("date");

        // Set data to TextViews
        tvName.setText("Customer Name: " + name);
        tvPhoneNumber.setText("Phone Number: " + mobile);
        tvVehicleName.setText("Vehicle Type: " + vehicleType);
        tvLocation.setText("Location: " + location);
        tvDate.setText("Date: " + date);

        // Call Customer button click listener
        callCustomerButton.setOnClickListener(v -> {
            // Use an Intent to start the dialer
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + mobile));
            startActivity(callIntent);
        });
    }
}
