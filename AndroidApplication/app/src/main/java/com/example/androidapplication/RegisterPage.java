package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button btnLoginLink = findViewById(R.id.login_link_btn);
        btnLoginLink.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginPage.class);
            startActivity(i);
        });
    }
}