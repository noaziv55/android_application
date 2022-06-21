package com.example.androidapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapplication.entities.Settings;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

/*        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_settings);*/

        Settings settings = new Settings();

        EditText serverDefault = findViewById(R.id.settingsServerAddress);
        serverDefault.setText("10.0.2.2:7019");

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            finish();
        });
    }
}