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


    @Query("SELECT * FROM message WHERE ((`from`=:fromId AND `to`=:toId) OR (`from`=:toId AND `to`=:fromId ))" )
    List<Message> index(String fromId, String toId);

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



}
