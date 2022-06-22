package com.example.androidapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapplication.entities.Settings;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

/*        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_settings);*/
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        String profileImg = intent.getStringExtra("profileImg");
        Settings settings = new Settings();

        EditText serverDefault = findViewById(R.id.settingsServerAddress);
        serverDefault.setText("10.0.2.2:7019");

        // todo: image doesn't show
        ImageView userProfileImg = findViewById(R.id.user_image_profile_image);
        Resources res = getResources();
        String mDrawableName = profileImg;
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        userProfileImg.setImageResource(resID);

        TextView userNickname = findViewById(R.id.user_text_nickname);
        userNickname.setText(nickname);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            finish();
        });
    }
}