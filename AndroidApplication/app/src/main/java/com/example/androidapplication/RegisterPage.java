package com.example.androidapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapplication.databinding.ActivityRegisterPageBinding;
import com.example.androidapplication.entities.User;
import com.example.androidapplication.viewModels.ContactsViewModel;
import com.example.androidapplication.viewModels.UsersViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterPage extends AppCompatActivity {
    private String encodedImage;
    private ActivityRegisterPageBinding binding;
    private UserDao userDao;
    private UsersViewModel usersViewModel;

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        binding = ActivityRegisterPageBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        setListeners();

        Button btnLoginLink = findViewById(R.id.login_link_btn);
        btnLoginLink.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginPage.class);
            startActivity(i);
        });

        TextView username = (TextView) findViewById(R.id.editTextTextPersonName);
        TextView nickname = (TextView) findViewById(R.id.editTextTextPersonNickname);
        TextView password = (TextView) findViewById(R.id.editTextTextPassword);
        TextView confirmPassword = (TextView) findViewById(R.id.editTextTextConfirmPassword);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String server = preferences.getString("server", "10.0.2.2:7000");

        if (usersViewModel == null || (usersViewModel.getUser(username.getText().toString())) == null) {
            usersViewModel = new UsersViewModel(this.getApplicationContext(),server);
        }

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            if (password.getText().toString().length() < 8) {
                showToast("Password must contain at least 8 characters");

            } else if (username.getText().toString().length() == 0 ||
                    nickname.getText().toString().length() == 0 ||
                    confirmPassword.getText().toString().length() == 0) {
                showToast("Please fill out all the fields");

            } else if (!(password.getText().toString().equals(confirmPassword.getText().toString()))) {
                showToast("passwords does not match");

            } else if (password.getText().toString().matches("[0-9]") || password.getText().toString().matches("[A-Za-z]+")) {
                showToast("password must contains numbers and letters");

            } else if (encodedImage == null) {
                showToast("Please select profile pic");
            }
            //need also to check from the api that there is exist already
            else {
                EditText username1 = findViewById(R.id.editTextTextPersonName);
                EditText nickname1 = findViewById(R.id.editTextTextPersonNickname);

                User user = usersViewModel.getUser(username1.getText().toString());
                if (user == null) {
                    User newUser = new User(username1.getText().toString(), nickname1.getText().toString(), password.getText().toString(), encodedImage, "server");
                    usersViewModel.add(newUser);
                        showToast("You registered successfully");
                } else {
                    showToast("you are already registered");
                }
            }
        });
    }

    private void setListeners() {
        //  binding.textSignIn.setOnClick
        binding.layoutImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });
    }
    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.imageProfile.setImageBitmap(bitmap);
                            encodedImage = encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}