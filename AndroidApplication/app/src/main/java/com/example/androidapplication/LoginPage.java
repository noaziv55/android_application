package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo place those two lines in each activity.
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();*/
        setContentView(R.layout.activity_login_page);

        Button btnRegisterLink = findViewById(R.id.register_link_btn);
        btnRegisterLink.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterPage.class);
            startActivity(i);
        });

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainPage.class);
            startActivity(intent);
        });
    }
}