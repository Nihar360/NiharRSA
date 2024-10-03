package com.example.niharrsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class welcome_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_user);
        Button lbutton = findViewById(R.id.button1);
        Button Sbutton = findViewById(R.id.button2);
        ImageView img = findViewById(R.id.imageView4);


        
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcome_user.this, user_login.class);
                startActivity(intent);
            }
        });
        Sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcome_user.this, user_SignIn.class);
                startActivity(intent);
            }
        });
    }

}