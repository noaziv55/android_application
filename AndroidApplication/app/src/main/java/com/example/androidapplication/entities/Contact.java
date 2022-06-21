package com.example.androidapplication.entities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"contactName","userName"})
public class Contact {
   // @PrimaryKey(autoGenerate = true)
    @NonNull
    private String contactName;
    @NonNull
    private String userName;
  //  @PrimaryKey(autoGenerate = true)
    private int profileImg;
    private String lastMassage;
    private String lastMassageSendingTime;
    private String server;

    public Contact(String userName, String contactName, int profileImg, String lastMassage, String lastMassageSendingTime, String server) {
        this.userName = userName;
        this.contactName = contactName;
        this.profileImg = profileImg;
        this.lastMassage = lastMassage;
        this.lastMassageSendingTime = lastMassageSendingTime;
        this.server= server;
    }


    public String getServer(){
        return server;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public String getLastMassageSendingTime() {
        return lastMassageSendingTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getContactName() {
        return contactName;
    }

}