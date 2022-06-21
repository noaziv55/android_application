package com.example.androidapplication.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String to;
    private String from;
    private String content;
    private String created;

    public Message(int id, String to, String from, String content, String created){
        this.id = id;
        this.to = to;
        this.from = from;
        this.content = content;
        this.created = created;
    }
    public int getId() {
        return id;
    }

    public String getTo() {return to; }

    public String getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }
}
