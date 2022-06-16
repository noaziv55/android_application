package com.example.androidapplication.entities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private String userName;
    @PrimaryKey(autoGenerate = true)
    private String contactName;
    private int profileImg;
    private String lastMassage;
    private String lastMassageSendingTime;

    public Contact(String userName, String contactName, int profileImg, String lastMassage, String lastMassageSendingTime) {
        this.userName = userName;
        this.contactName = contactName;
        this.profileImg = profileImg;
        this.lastMassage = lastMassage;
        this.lastMassageSendingTime = lastMassageSendingTime;
    }

    public int getPictureId() {
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