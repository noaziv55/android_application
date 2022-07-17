package com.example.androidapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.viewModels.ContactsViewModel;

public class AddContactActivity extends AppCompatActivity {

    public static ContactsViewModel contactsViewModel;
    public static Context context;
    //private AppDB db;
    //  private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        context = getApplicationContext();

        Intent loginIntent = getIntent();
        String username = loginIntent.getStringExtra("username");
        EditText etNickname = findViewById(R.id.etNickname);
        EditText etServer = findViewById(R.id.etServer);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String server = preferences.getString("server","10.0.2.2:7000");

        if (contactsViewModel == null || !(username.equals(contactsViewModel.getUsername()))) {
            contactsViewModel = new ContactsViewModel(this.getApplicationContext(),username, server);
        }
//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, username)
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build();

        // contactDao = db.contactDao();
        Button btnAddContact = findViewById(R.id.bthAddContact);
        btnAddContact.setOnClickListener(v->{
            Contact contact = new Contact(username,etNickname.getText().toString(),etNickname.getText().toString(),
                    R.drawable.default_profile_image,null,null,
                    etServer.getText().toString());
            contactsViewModel.add(contact);
            //  contactDao.insert(contact);
            finish();
        });
    }
}