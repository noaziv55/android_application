package com.example.androidapplication.repositories;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.AppDB;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.UserDao;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;

import java.util.LinkedList;
import java.util.List;

public class UsersRepository {
    private UserDao dao;
    private UsersListData usersListData;
    private AppDB db;
    //private PostAPI api;


    public UsersRepository (Context context, String roomName) {
        this.db = AppDB.getInstance(context);
        this.dao = db.userDao();
        this.usersListData = new UsersListData();
    }

    class UsersListData extends MutableLiveData<List<User>> {
        public UsersListData() {
            super();
            setValue(new LinkedList<User>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                usersListData.postValue(dao.index());
            }).start();
        }
    }

    public LiveData<List<User>> getAll() {
        return usersListData;
    }

    public void add(final User user) {
        dao.insert(user);
        usersListData.postValue(dao.index());
        // api.add(post);
    }
    public User hasUser(String username){
        return dao.get(username);
    }

//    public List<User> refresh(){
//        return dao.index();
//    }

//    public void delete(final Post post) {
//        api.delete(post);
//    }
//
//    public void reload() {
//        api.get();
//    }
}