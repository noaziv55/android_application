package com.example.androidapplication;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> index();

    @Query("SELECT * FROM user WHERE username = :username")
    User get(String username);

    @Insert
    void insert(User...users);

    @Update
    void update(User...users);

    //void delete(User...users);

    @Query("DELETE FROM contact WHERE ContactOfUser=:username")
    void deleteAll(String username);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Contact> contacts);
}