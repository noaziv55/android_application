package com.example.androidapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Message;
import com.example.androidapplication.entities.User;

//@Database(entities = {Message.class,Contact.class},version = 2)
@Database(entities = {Message.class, Contact.class, User.class},version = 1 , exportSchema = false)
public abstract class AppDB extends RoomDatabase {

 private static AppDB appDB;

 public static AppDB getInstance(Context context) {
  if (appDB == null) {
   appDB = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "AppDB")
           .allowMainThreadQueries().build();
  }
  return appDB;
 }
  public abstract ContactDao contactDao ();
  public abstract MessageDao messageDao ();
  public abstract UserDao userDao();


}
