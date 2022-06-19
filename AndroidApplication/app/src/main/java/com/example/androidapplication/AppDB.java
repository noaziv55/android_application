package com.example.androidapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidapplication.entities.Contact;

@Database(entities = {Contact.class},version = 1)
public abstract class AppDB extends RoomDatabase {
 public abstract ContactDao contactDao();

}
