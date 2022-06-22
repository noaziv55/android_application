package com.example.androidapplication.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.androidapplication.entities.User;
import com.example.androidapplication.repositories.UsersRepository;

import java.util.List;

public class UsersViewModel {
    private UsersRepository repository;
    private LiveData<List<User>> users;
    private String username;



    public UsersViewModel(Context context, String username){
        this.repository = new UsersRepository(context, username);
        this.users= repository.getAll();
        this.username=username;
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public String getUsername(){
        return username;
    }

    //not good
//    public List<User> refresh(){
//        return this.repository.refresh();
//    }

    public void add(User user) {
        repository.add(user); }

//    public void delete(User user) { repository.delete(user); }
//    public void reload() { repository.reload(); }
}
