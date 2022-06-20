package com.example.androidapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapplication.databinding.ActivityRegisterPageBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterPage extends AppCompatActivity {
    private String encodedImage;
    private ActivityRegisterPageBinding binding;

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
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

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().length()<8) {
                    showToast("Password should contain at least 8 characters");
                }
                else if (username.getText().toString().length()==0 ||
                        nickname.getText().toString().length()==0 ||
                        confirmPassword.getText().toString().length()==0 ){
                    showToast("Please fill out all the fields");
                }
                else if (!(password.getText().toString().equals( confirmPassword.getText().toString())) ){
                    showToast("passwords does not match");
                }
                else if(password.getText().toString().matches("[0-9]") ||password.getText().toString().matches("[A-Za-z]+")){
                    showToast("password must contains numbers and letters");
                }
                else if (encodedImage == null){
                    showToast("Please select profile pic");
                }
                else{
                    showToast("You registered successfully");
                    EditText username = findViewById(R.id.editTextTextPersonName);
                    EditText nickname = findViewById(R.id.editTextTextPersonNickname);
                    // EditText password = findViewById(R.id.editTextTextPassword);
//                    EditText server = findViewById(R.id.server);
//                    User user= new User(username.getText().toString(),nickname.getText().toString(),password.getText().toString() )
                }

            }

        });

    }
    private void setListeners(){
        //  binding.textSignIn.setOnClick
        binding.layoutImage.setOnClickListener(v -> {
            Intent intent= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });
    }

    private void signUp(){
    }
    private String encodeImage(Bitmap bitmap){
        int previewWidth =150;
        int perviewHeight =bitmap.getHeight() * previewWidth/ bitmap.getWidth();
        Bitmap previewBitmap =Bitmap.createScaledBitmap(bitmap, previewWidth,perviewHeight,false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] bytes= byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }
    private final ActivityResultLauncher<Intent> pickImage =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode()== RESULT_OK){
                    if(result.getData()!=null){
                        Uri imageUri =result.getData().getData();
                        try{
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.imageProfile.setImageBitmap(bitmap);
                            encodedImage =encodeImage(bitmap);
                        }
                        catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    private Boolean isValidSignUpDetails(){
        if (encodedImage == null){
            showToast("Select profile pic");
            return false;
        }
        return true;
    }
//    private void loading(Boolean isLoading){
//        if (isLoading){
//            binding.btnRegister.setVisibility(View.INVISIBLE);
//            binding.pro
//        }
//    }
}