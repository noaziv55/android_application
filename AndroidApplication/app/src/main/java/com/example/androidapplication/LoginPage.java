package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapplication.entities.User;
import com.example.androidapplication.viewModels.UsersViewModel;

public class LoginPage extends AppCompatActivity {

    private UserDao userDao;
    private UsersViewModel usersViewModel;


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo place those two lines in each activity.
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();*/
        setContentView(R.layout.activity_login_page);

        Button btnRegisterLink = findViewById(R.id.register_link_btn);
        btnRegisterLink.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterPage.class);
            startActivity(i);
        });

        TextView username = (TextView) findViewById(R.id.editTextTextPersonName);
        TextView password = (TextView) findViewById(R.id.editTextTextPassword);

        if (usersViewModel == null || (usersViewModel.getUser(username.getText().toString())) == null) {
            usersViewModel = new UsersViewModel(this.getApplicationContext());
        }

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {

            if ((username.getText().toString().equals("")) || (password.getText().toString().equals(""))) {
                showToast("Please fill all fields properly");
            } else if (password.getText().toString().length() < 8) {
                showToast("Password must contain at least 8 characters");
            } else {
//                AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, username.getText().toString())
//                        .fallbackToDestructiveMigration()
//                        .allowMainThreadQueries()
//                        .build();
                // db.clearAllTables();
                // userDao = db.userDao();
                //User user = userDao.get(username.getText().toString());
                User user = usersViewModel.getUser(username.getText().toString());
                if (user == null) {
                    showToast("User doesn't exist");
                } else if (!(password.getText().toString().equals(user.getPassword()))) {
                    showToast("Password is not current");
                } else {
                    //create an intent
                    String intentUsername = username.getText().toString();
                    String intentNickname = user.getNickname();
                    String intentProfileImg = user.getProfileImg();
                    Intent intent = new Intent(getApplicationContext(), MainPage.class);
                    intent.putExtra("username", intentUsername);
                    intent.putExtra("nickname", intentNickname);
                    intent.putExtra("profileImg", intentProfileImg);
                    EditText clearUsername = findViewById(R.id.editTextTextPersonName);
                    clearUsername.getText().clear();
                    EditText clearPassword = findViewById(R.id.editTextTextPassword);
                    clearPassword.getText().clear();
                    startActivity(intent);
                }
            }
        });
    }
}