package com.example.niharrsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class admin1 extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1); // Make sure this points to the correct layout file

        // Get references to the buttons
        Button btnRequest = findViewById(R.id.button1);
        Button btnAllMechanics = findViewById(R.id.btn_all_mechanics);
        Button btnAddMechanics = findViewById(R.id.btn_add_mechanics);
        Button btnSearchMechanics = findViewById(R.id.btn_search_mechanics);
        Button btnLogOut = findViewById(R.id.btn_logout);

        // Initialize Firebase instances
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Set up onClickListeners (if necessary)
        btnRequest.setOnClickListener(view -> {
            Intent intent = new Intent(admin1.this, AdminRequestsActivity.class);
            startActivity(intent);
        });

        btnAllMechanics.setOnClickListener(view -> {
            Intent intent = new Intent(admin1.this, MechanicListActivity.class);
            startActivity(intent);
        });

        btnAddMechanics.setOnClickListener(view -> {
            Intent intent = new Intent(admin1.this, AddMechanicActivity.class);
            startActivity(intent);
        });

        btnSearchMechanics.setOnClickListener(view -> {
            Intent intent = new Intent(admin1.this, search_Mech.class);
            startActivity(intent);
        });

        btnLogOut.setOnClickListener(view -> {
            logoutUser();
        });
    }

    private void logoutUser() {
        firebaseAuth.signOut();
        Toast.makeText(admin1.this, "Logged out successfully", Toast.LENGTH_SHORT).show();

        // Redirect to Login Activity
        Intent intent = new Intent(admin1.this, admin_login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close the current activity
    }
}
