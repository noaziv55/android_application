package com.example.androidapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidapplication.entities.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact WHERE ContactOfUser = :username")
    List<Contact> index(String username);

    @Query("SELECT * FROM contact WHERE name = :contactName")
    Contact get(String contactName);

    @Insert
    void insert(Contact...contacts);

    @Update
    void update(Contact...contacts);

    @Query("DELETE FROM contact WHERE ContactOfUser=:username")
    void deleteAll(String username);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Contact> contacts);
}