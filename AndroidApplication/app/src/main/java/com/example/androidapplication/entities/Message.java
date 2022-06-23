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
    private boolean sent;

    public Message(int id, String to, String from, String content, String created, boolean sent){
        this.id = id;
        this.to = to;
        this.from = from;
        this.content = content;
        this.created = created;
        this.sent = sent;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isSent(){
        return sent;
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