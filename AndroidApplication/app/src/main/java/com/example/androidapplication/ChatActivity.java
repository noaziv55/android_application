package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    ImageView profilePictureView;
    TextView contactNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        profilePictureView = findViewById(R.id.user_image_profile_image);
        contactNameView = findViewById(R.id.user_text_user_name);

        Intent activityIntent = getIntent();

        if (activityIntent != null) {
            String contactName = activityIntent.getStringExtra("contactName");
            int profilePicture = activityIntent.getIntExtra("profilePicture", R.drawable.default_profile_image);

            profilePictureView.setImageResource(profilePicture);
            contactNameView.setText(contactName);
        }
    }
}