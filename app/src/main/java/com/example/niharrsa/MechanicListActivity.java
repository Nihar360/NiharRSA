package com.example.niharrsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MechanicListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MechanicAdapter adapter;
    private List<MechanicModel> mechanicList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_list);

        // Initialize Firestore and RecyclerView
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.mechanicRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize mechanic list and adapter
        mechanicList = new ArrayList<>();
        adapter = new MechanicAdapter(mechanicList, this);
        recyclerView.setAdapter(adapter);

        // Fetch data from Firestore
        db.collection("mechanics").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : list) {
                            MechanicModel mechanic = document.toObject(MechanicModel.class);
                            mechanic.setId(document.getId());  // Set document ID
                            mechanicList.add(mechanic);
                            Log.d("FirestoreData", "Fetched mechanic: " + mechanic.toString()); // Log fetched mechanic
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MechanicListActivity.this, "No Mechanics Found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MechanicListActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });


    }
}
