package com.example.niharrsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class admin1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1); // Make sure this points to the correct layout file

        // Get references to the buttons
        Button btnRequest = findViewById(R.id.button1);
        Button btnAllMechanics = findViewById(R.id.btn_all_mechanics);
        Button btnAddMechanics = findViewById(R.id.btn_add_mechanics);
//        Button btnSearchMechanics = findViewById(R.id.btn_search_mechanics);


        // Set up onClickListeners (if necessary)
        btnRequest.setOnClickListener(view -> {
            Intent intent = new Intent(admin1.this, AdminRequestsActivity.class);
            startActivity(intent);
        });
//
        btnAllMechanics.setOnClickListener(view -> {
            Intent intent = new Intent(admin1.this, MechanicListActivity.class);
            startActivity(intent);
        });
//
        btnAddMechanics.setOnClickListener(view -> {
            Intent intent = new Intent(admin1.this, AddMechanicActivity.class);
            startActivity(intent);
        });
//
//        btnSearchMechanics.setOnClickListener(view -> {
//            // Handle search mechanics button click
//        });
    }
}
