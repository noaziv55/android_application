package com.example.androidapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidapplication.entities.Contact;

public class AddContactActivity extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();
        Button btnAddContact = findViewById(R.id.bthAddContact);
        btnAddContact.setOnClickListener(v->{
            EditText etNickname = findViewById(R.id.etNickname);
            EditText etServer = findViewById(R.id.etServer);
            Contact contact = new Contact("ADMIN",etNickname.getText().toString(),
                    R.drawable.blue,"hello","20:00",
                    etServer.getText().toString());
            contactDao.insert(contact);
            finish();
        });
    }
}