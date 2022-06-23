package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapplication.adapters.MessageListAdapter;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Message;
import com.example.androidapplication.viewModels.ContactsViewModel;
import com.example.androidapplication.viewModels.MessagesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    // public static Contact currentContact;
    ImageView profilePictureView;
    TextView contactNameView;
    private MessageListAdapter adapter;
    private RecyclerView listMessages;
    private MessagesViewModel messageViewModel;
    private ContactsViewModel contactsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        profilePictureView = findViewById(R.id.user_image_profile_image);
        contactNameView = findViewById(R.id.user_text_user_name);

        Intent activityIntent = getIntent();
        String contactName = activityIntent.getStringExtra("contactName");
        String username = activityIntent.getStringExtra("username");
        int profilePicture = activityIntent.getIntExtra("profilePicture", R.drawable.default_profile_image);

        profilePictureView.setImageResource(profilePicture);
        contactNameView.setText(contactName);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String server = preferences.getString("server", "10.0.2.2:7000");

        if (contactsViewModel == null || !(username.equals(contactsViewModel.getUsername()))) {
            contactsViewModel = new ContactsViewModel(this.getApplicationContext(), username, server);
        }
        listMessages = findViewById(R.id.listMessages);
        adapter = new MessageListAdapter(this);
        listMessages.setAdapter(adapter);
        listMessages.setLayoutManager(new LinearLayoutManager(this));
        messageViewModel = new MessagesViewModel(this.getApplicationContext(),
                username, contactName);

        messageViewModel.getMessages().observe(this, messages -> {
            adapter.setMessages(messages);
            listMessages.scrollToPosition(messages.size() - 1);
        });

        FloatingActionButton btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            //content
            EditText etContent = findViewById(R.id.msgBox);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            Message message = new Message(0, contactNameView.getText().toString(), username,
                    etContent.getText().toString(), formattedDate, true);
            messageViewModel.add(message);
            Contact currentContact = contactsViewModel.getContact(contactName, username);
            currentContact.setLast(etContent.getText().toString());
            currentContact.setLastdate(formattedDate);
            contactsViewModel.updateContact(currentContact);
            etContent.getText().clear();
            messageViewModel.getMessages().observe(this, messages -> {

                if (messages.size() > 0) adapter.setMessages(messages);
                listMessages.scrollToPosition(messages.size() - 1);
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}