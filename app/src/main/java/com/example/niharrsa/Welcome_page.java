package com.example.niharrsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class Welcome_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_page);
        Button ad_button = findViewById(R.id.admin_button);
        Button user_button = findViewById(R.id.user_button);

        ad_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Welcome_page.this, admin_welcome.class);
                startActivity(intent);
            }
        });
        user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Welcome_page.this, welcome_user.class);
                startActivity(intent);
            }
        });


    }
}