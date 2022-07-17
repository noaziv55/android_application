package com.example.androidapplication.entities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (primaryKeys = {"ContactOfUser", "id"})
//@Entity (primaryKeys = {"name"})
public class Contact {
    // @PrimaryKey(autoGenerate = true)
    @NonNull
    private String id;
    @NonNull
    private String ContactOfUser;
    @NonNull
    private String name;
    private int profileImg;
    private String last;
    private String lastdate;
    private String server;

    public Contact(@NonNull String ContactOfUser, @NonNull String id, @NonNull String name, int profileImg, String last, String lastdate, String server) {
        this.name = name;
        this.profileImg = profileImg;
        this.last = last;
        this.lastdate = lastdate;
        this.server = server;
        this.ContactOfUser = ContactOfUser;
        this.id =id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @Ignore
    public Contact(@NonNull String name, @NonNull String ContactOfUser, String server) {
        this.name = name;
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