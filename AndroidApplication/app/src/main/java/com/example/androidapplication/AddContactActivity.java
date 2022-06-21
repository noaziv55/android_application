package com.example.androidapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.viewModels.ContactsViewModel;

public class AddContactActivity extends AppCompatActivity {

    public static ContactsViewModel contactsViewModel;
    //private AppDB db;
  //  private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Intent loginIntent = getIntent();
        String username = loginIntent.getStringExtra("username");
        if (contactsViewModel == null || !(username.equals(contactsViewModel.getUsername()))) {
            contactsViewModel = new ContactsViewModel(this.getApplicationContext(),username);
        }
//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, username)
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build();

       // contactDao = db.contactDao();
        Button btnAddContact = findViewById(R.id.bthAddContact);
        btnAddContact.setOnClickListener(v->{
            EditText etNickname = findViewById(R.id.etNickname);
            EditText etServer = findViewById(R.id.etServer);
            Contact contact = new Contact(username,etNickname.getText().toString(),
                    R.drawable.default_profile_image,null,null,
                    etServer.getText().toString());
            contactsViewModel.add(contact);
          //  contactDao.insert(contact);
            finish();
        });
    }
}