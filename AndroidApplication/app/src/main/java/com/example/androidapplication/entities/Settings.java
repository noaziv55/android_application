package com.example.androidapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {
    @PrimaryKey
    @NonNull
    private String server;

    public Settings() {
        this.server = "http://10.0.2.2:7019";
    }

    @NonNull
    public String getServer() {
        return server;
    }

    public int setServer(@NonNull String server) {
        this.server = server;
        return 0;
    }
}