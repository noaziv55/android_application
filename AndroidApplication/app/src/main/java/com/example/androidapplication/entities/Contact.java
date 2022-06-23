package com.example.androidapplication.entities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (primaryKeys = {"name", "ContactOfUser"})
//@Entity (primaryKeys = {"name"})
public class Contact {
    // @PrimaryKey(autoGenerate = true)
    @NonNull
    private String name;
    @NonNull
    private String ContactOfUser;
    private int profileImg;
    private String last;
    private String lastdate;
    private String server;

    public Contact(@NonNull String name, @NonNull String ContactOfUser, int profileImg, String last, String lastdate, String server) {
        this.name = name;
        this.profileImg = profileImg;
        this.last = last;
        this.lastdate = lastdate;
        this.server = server;
        this.ContactOfUser = ContactOfUser;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getContactOfUser() {
        return ContactOfUser;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setContactOfUser(@NonNull String contactOfUser) {
        ContactOfUser = contactOfUser;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public String getServer() {
        return server;
    }
}