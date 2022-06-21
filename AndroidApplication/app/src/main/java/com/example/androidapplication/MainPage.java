package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidapplication.adapters.CustomListAdapter;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainPage extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    RecyclerView list_view;
    CustomListAdapter adapter;
    CustomListAdapter.RecyclerViewClickListener listener;
    List<Contact> contacts;
    User user;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent loginIntent = getIntent();
//        user = (User) loginIntent.getSerializableExtra("username");
        String username = loginIntent.getStringExtra("username");
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, username)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        //db.clearAllTables();

        contactDao = db.contactDao();
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        contacts = contactDao.index();
        //contacts = new ArrayList<>();
        list_view = findViewById(R.id.list_view);
        setOnClickListener();
        adapter = new CustomListAdapter(this, listener);
        list_view.setAdapter(adapter);
        list_view.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(contacts);
        list_view.setClickable(true);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            intent.putExtra("contactName", contacts.get(position).getContactName());
            intent.putExtra("profilePicture", contacts.get(position).getProfileImg());
            startActivity(intent);
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }
}