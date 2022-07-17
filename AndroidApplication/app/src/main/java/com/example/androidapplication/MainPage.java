package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;

import com.example.androidapplication.API.ContactsAPI;
import com.example.androidapplication.adapters.CustomListAdapter;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;
import com.example.androidapplication.viewModels.ContactsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainPage extends AppCompatActivity {

    private AppDB db;
    private ContactsViewModel contactsViewModel;
    private ContactDao contactDao;
    private RecyclerView list_view;
    private CustomListAdapter adapter;
    private CustomListAdapter.RecyclerViewClickListener listener;
    private List<Contact> contacts;
    private String username;
    private String nickname;
    private String profileImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent loginIntent = getIntent();
//        user = (User) loginIntent.getSerializableExtra("username");
        username = loginIntent.getStringExtra("username");
        nickname = loginIntent.getStringExtra("nickname");
        profileImg = loginIntent.getStringExtra("profileImg");
//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, username)
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build();

        //db.clearAllTables();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String server = preferences.getString("server", "10.0.2.2:7000");

        //ContactsAPI contactsAPI = new ContactsAPI(contactListData, dao, server);

        if (contactsViewModel == null || !(username.equals(contactsViewModel.getUsername()))) {
            contactsViewModel = new ContactsViewModel(this.getApplicationContext(), username, server);
        }
        //contacts = (List<Contact>) contactsViewModel.getContacts();
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
            intent.putExtra("contactName", contactsViewModel.getContacts().getValue().get(position).getName());
            intent.putExtra("profilePicture", contactsViewModel.getContacts().getValue().get(position).getProfileImg());
            intent.putExtra("username", username);
            startActivity(intent);
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        //contactsViewModel.refresh();
        //contacts.clear();
        //contacts.addAll(contactsViewModel.refresh());
        adapter.notifyDataSetChanged();


        //need to be
        //super.onResume();
        //contactsViewModel.refresh();

//        contacts.clear();
//        contacts.addAll(contactDao.index());
//        adapter.notifyDataSetChanged();
    }
}