package com.example.androidapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import com.example.androidapplication.entities.Message;
@Dao
public interface MessageDao {
//check why red


    @Query("SELECT * FROM message WHERE ((`from`=:from AND `to`=:to) OR (`from`=:to AND `to`=:from ))" )
    List<Message> index(String from, String to);

    @Query("SELECT * FROM message")
    List<Message> getAllData();

    @Query("SELECT * FROM message WHERE id =:id")
    Message get(int id);

    @Insert
    void insert(Message... messages);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Message> messages);

    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);

    @Query("DELETE FROM message WHERE ((`from`=:from AND `to`=:to) OR (`from`=:from AND `to`=:to ))")
    void deleteAll(String from, String to);



}