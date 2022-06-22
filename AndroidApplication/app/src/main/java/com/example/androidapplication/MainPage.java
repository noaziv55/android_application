package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.lifecycle.ViewModelProvider;
import com.example.androidapplication.viewModels.ContactsViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.androidapplication.adapters.CustomListAdapter;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainPage extends AppCompatActivity {

    private AppDB db;
    private ContactsViewModel contactsViewModel;
    private ContactDao contactDao;
    RecyclerView list_view;
    CustomListAdapter adapter;
    CustomListAdapter.RecyclerViewClickListener listener;
    //List<Contact> contacts;

    String username;
    String nickname;
    String profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent loginIntent = getIntent();
        username = loginIntent.getStringExtra("username");
        nickname = loginIntent.getStringExtra("nickname");
        profileImg = loginIntent.getStringExtra("profileImg");
        //db = Room.databaseBuilder(getApplicationContext(), AppDB.class, username)
        //        .fallbackToDestructiveMigration()
        //        .allowMainThreadQueries()
        //       .build();

        //db.clearAllTables();

        //contactDao = db.contactDao();

        if (contactsViewModel == null || !(username.equals(contactsViewModel.getUsername()))) {
            contactsViewModel = new ContactsViewModel(this.getApplicationContext(),username);
        }
        //contacts = (List<Contact>) contactsViewModel.getContacts();
        //contactDao = db.contactDao();
        //contactDao = db.contactDao();
        //  contacts = contactDao.index();
        //contacts = new ArrayList<>();
        list_view = findViewById(R.id.list_view);
        setOnClickListener();
        adapter = new CustomListAdapter(this, listener);
        list_view.setAdapter(adapter);
        list_view.setLayoutManager(new LinearLayoutManager(this));
        //adapter.setContacts(contacts);
        list_view.setClickable(true);
        contactsViewModel.getContacts().observe(this,contacts ->{
                adapter.setContacts(contacts);
                //did refresh also
            });


        ImageButton btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsPage.class);
            intent.putExtra("nickname", nickname);
            intent.putExtra("profileImg", profileImg);
            startActivity(intent);
        });

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            intent.putExtra("contactName", contactsViewModel.getContacts().getValue().get(position).getContactName());
            intent.putExtra("profilePicture", contactsViewModel.getContacts().getValue().get(position).getProfileImg());
            startActivity(intent);
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        //contacts.clear();
        //contacts.addAll(contactsViewModel.refresh());
        adapter.notifyDataSetChanged();

        //need to be
        //....................
        //super.onResume();
        //contactsViewModel.refresh();
        //....................
//        contacts.clear();
//        contacts.addAll(contactDao.index());
//        adapter.notifyDataSetChanged();
    }
}

