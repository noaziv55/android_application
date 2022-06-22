package com.example.androidapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String username;
    private String nickname;
    private String password;
    private String serverAddress;
    private String profileImg;

    public User(@NonNull String username, String nickname, String password, String profileImg, String serverAddress) {
        this.username = username;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.serverAddress = serverAddress;
        this.password = password;
    }
    public String getServerAddress(){
        return serverAddress;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }


}