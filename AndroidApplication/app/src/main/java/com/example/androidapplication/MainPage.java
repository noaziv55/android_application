package com.example.androidapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.androidapplication.adapters.CustomListAdapter;
import com.example.androidapplication.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {


    //private ContactsViewModel viewModel;
//
//    String username = "Admin";
//
//    final private int[] profilePictures = {
//            R.drawable.blue
//    };
//
//    final private String[] contactNames = {
//            "Blue User", "Golden User", "Green User", "Red User", "Lightblue User", "Pink User"
//    };
//
//    final private String[] lastMassages = {
//            "Hi, how are you?", "24K Magic", "I'm GREEN!", "Red is my name", "wasap :)", "Yo!"
//    };
//
//    final private String[] times = {
//            "12:00", "00:30", "3:23", "8:59", "14:52", "12:23"
//    };


    private AppDB db;
    private ContactDao contactDao;
    RecyclerView list_view;
    CustomListAdapter adapter;
    CustomListAdapter.RecyclerViewClickListener listener;
    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactDB")
                .allowMainThreadQueries()
                .build();

        //db.clearAllTables();


        contactDao = db.contactDao();

//        if (contacts.isEmpty()){
//            Log.i("mainPage","list is empty");
//        }
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddContactActivity.class);
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
//        if (contacts.isEmpty()){
//            Log.i("mainPage","list is empty2");
//        }
        //viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);


        //adapter = new CustomListAdapter(getApplicationContext(), users);


        //contacts = new ArrayList<>();
//
//        for (int i = 0; i < 4; i++) {
//            Contact aContact = new Contact(
//                    username, contactNames[i], profilePictures[0],
//                    lastMassages[i], times[i]
//            );
//            contacts.add(aContact);
//        }


/*        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view->new Thread(()->{
            User contact = new User(username, "Alice", profilePictures[0], lastMassages[0], times[0]);
            viewModel.add(post);
        }).start());
        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(()->{
            viewModel.reload();
        });
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
            refreshLayout.setOnRefreshing(false);
        });*/


        list_view.setClickable(true);

        /*list_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.putExtra("contactName", contactNames[i]);
                intent.putExtra("profilePicture", profilePictures[i]);
                intent.putExtra("lastMassage", lastMassages[i]);
                intent.putExtra("time", times[i]);
                startActivity(intent);
            }
        });*/

    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
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