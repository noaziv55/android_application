package com.example.androidapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.viewModels.ContactsViewModel;
import com.example.androidapplication.viewModels.UsersViewModel;

public class AddContactActivity extends AppCompatActivity {

    public static ContactsViewModel contactsViewModel;
    public static UsersViewModel usersViewModel;
    //private AppDB db;
    //  private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Intent loginIntent = getIntent();
        String username = loginIntent.getStringExtra("username");
        EditText etNickname = findViewById(R.id.etNickname);
        EditText etServer = findViewById(R.id.etServer);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String server = preferences.getString("server","10.0.2.2:7000");

        if (contactsViewModel == null || !(username.equals(contactsViewModel.getUsername()))) {
            contactsViewModel = new ContactsViewModel(this.getApplicationContext(),username, server);
        }

        if (usersViewModel == null || (usersViewModel.getUser(username)) == null) {
            usersViewModel = new UsersViewModel(this.getApplicationContext(),server);
        }
        // contactDao = db.contactDao();
        Button btnAddContact = findViewById(R.id.bthAddContact);
        btnAddContact.setOnClickListener(v->{
            if (usersViewModel.getUser(etNickname.getText().toString())!= null) {
                Contact contact = new Contact(username, etNickname.getText().toString(),
                        R.drawable.default_profile_image, null, null,
                        etServer.getText().toString());
                contactsViewModel.add(contact);
                //  contactDao.insert(contact);
            }
            else{
                Toast.makeText(getApplicationContext(), "user doesn't exist", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}