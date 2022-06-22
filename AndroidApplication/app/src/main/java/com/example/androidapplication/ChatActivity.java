package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapplication.adapters.MessageListAdapter;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    //  public static Contact currentContact;
    ImageView profilePictureView;
    TextView contactNameView;
    private AppDB db;
    private MessageDao messageDao;
    private List<Message> messages;
    private MessageListAdapter adapter;
    private RecyclerView listMessages;


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
        //    contactNameView.setText(currentContact.getContactName());
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessageDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

//        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshSingleChat);
//        swipeRefreshLayout.setEnabled(false);
        //db.clearAllTables();

        messageDao = db.messageDao();
        messages = messageDao.index("Me",contactNameView.getText().toString());
        //change to the id noa gave it
        listMessages = findViewById(R.id.listMessages);
        adapter = new MessageListAdapter(this);
        listMessages.setAdapter(adapter);
        listMessages.setLayoutManager(new LinearLayoutManager(this));
        //change to a send button
        FloatingActionButton btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v->{
            //content
            EditText etContent = findViewById(R.id.msgBox);
            //change "Me" to user.getUsername
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            Message message = new Message(0,contactNameView.getText().toString(), "Me", etContent.getText().toString(),formattedDate);
            messageDao.insert(message);
            etContent.getText().clear();
        });
        adapter.setMessages(messages);
//       finish();
//       startActivity(getIntent());
//        if (messages.size() > 0){
//            swipeRefreshLayout.setRefreshing(false);
//            listMessages.scrollToPosition(messages.size() - 1);
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        messages.clear();
        messages.addAll(messageDao.index("Me",contactNameView.getText().toString()));
        adapter.notifyDataSetChanged();
    }
}