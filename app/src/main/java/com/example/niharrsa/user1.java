package com.example.niharrsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class user1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_1); // Assuming your layout file is named activity_main.xml

        // Find the button by its ID
        MaterialButton bookButton = findViewById(R.id.buttonBook);

        // Set an onClickListener for the button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to XYZActivity
                Intent intent = new Intent(user1.this,user3.class);
                startActivity(intent);
            }
        });
    }
}
