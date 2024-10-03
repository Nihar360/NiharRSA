package com.example.niharrsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class admin_welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_welcome);
        Button logbutton = findViewById(R.id.button1);
        Button sigbutton = findViewById(R.id.button2);

        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_welcome.this, admin_login.class);
                startActivity(intent);
            }
        });
        sigbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_welcome.this, admin_signup.class);
                startActivity(intent);
            }
        });



    }
}