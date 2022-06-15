package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    RecyclerView listContacts = findViewById(R.id.listContacts);
    /*GridAdapter gridAdapter = new GridAdapter(this, images);
 lstGrid.setAdapter(gridAdapter);
 lstGrid.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.View
*/
}