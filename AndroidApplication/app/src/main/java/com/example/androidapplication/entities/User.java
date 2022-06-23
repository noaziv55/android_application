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
    private String server;
    private String profileImg;

    public User(@NonNull String username, String nickname, String password, String profileImg, String server) {
        this.username = username;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.server = server;
        this.password = password;
    }
    public String getServer(){
        return server;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getPassword() {
        return password;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }
    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

}