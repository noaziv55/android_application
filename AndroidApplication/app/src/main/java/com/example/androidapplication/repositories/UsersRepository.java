package com.example.androidapplication.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.API.ContactsAPI;
import com.example.androidapplication.API.UsersAPI;
import com.example.androidapplication.AppDB;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.R;
import com.example.androidapplication.UserDao;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;

import java.util.LinkedList;
import java.util.List;

public class UsersRepository {
    private UserDao dao;
    private UsersListData usersListData;
    private AppDB db;
    private String username;
    private String server;
    private UsersAPI api;

    public String getUsername() {
        return username;
    }

    public UsersRepository(Context context, String username, String server) {
        this.db = AppDB.getInstance(context);
        this.dao = db.userDao();
        this.usersListData = new UsersListData();
        this.username = username;
        this.server = server;
        this.api = new UsersAPI(usersListData, dao, server);
        //api.get(this, username);
    }

    class UsersListData extends MutableLiveData<List<User>> {
        public UsersListData() {
            super();
            List<User> users = new LinkedList<User>();
            setValue(users);
        }

        //@Override
/*        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                contactsListData.postValue(dao.index(username));
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }).start();
        }*/

        class PrimeThread extends Thread {
            public void run() {
                List<User> a = dao.index();
                List<User> allList = dao.index();
                usersListData.postValue(dao.index());
            }
        }

        @Override
        protected void onActive() {
            super.onActive();
            PrimeThread t = new PrimeThread();
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public void handleAPIDataUsers(int status, List<User> users) {
//        if (status == 200) {
//
//            new Thread(() -> {
//                this.dao.deleteAll(this.username);
//                //this.dao.insertAll(updateContactsFields(contacts));
//                for (User user: users){
//                    user.setNickname(this.username);
//                    user.setProfileImg("photo");
//                    this.dao.insert(user);
//                }
//                this.usersListData.postValue(users);
//            }).start();
//        }
//    }

    public void postHandle(User user, int status){
        if (status == 200) {
            new Thread(() -> {
                this.dao.insert(user);
              //  this.usersListData.postValue(dao.index());
            }).start();
        }
    }
    private List<Contact> updateContactsFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setContactOfUser(username);
        }
        return contacts;
    }

    public LiveData<List<User>> getAll() {
        return usersListData;
    }

    public void add(final User user) {
      //  dao.insert(user);
         api.post(this, user);
    }
        public User hasUser(String username){
        return dao.get(username);
    }

//    public List<Contact> refresh(){
//        return dao.index();
//    }

//    public void delete(final Post post) {
//        api.delete(post);
//    }

/*    public void reload() {
        api.get(this);
    }*/
}

//package com.example.androidapplication.repositories;
//import android.content.Context;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.androidapplication.AppDB;
//import com.example.androidapplication.ContactDao;
//import com.example.androidapplication.UserDao;
//import com.example.androidapplication.entities.Contact;
//import com.example.androidapplication.entities.User;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class UsersRepository {
//    private UserDao dao;
//    private UsersListData usersListData;
//    private AppDB db;
//    //private PostAPI api;
//
//
//    public UsersRepository (Context context, String roomName) {
//        this.db = AppDB.getInstance(context);
//        this.dao = db.userDao();
//        this.usersListData = new UsersListData();
//    }
//
//    class UsersListData extends MutableLiveData<List<User>> {
//        public UsersListData() {
//            super();
//            setValue(new LinkedList<User>());
//        }
//
//        @Override
//        protected void onActive() {
//            super.onActive();
//            new Thread(() -> {
//                usersListData.postValue(dao.index());
//            }).start();
//        }
//    }
//
//    public LiveData<List<User>> getAll() {
//        return usersListData;
//    }
//
//    public void add(final User user) {
//        dao.insert(user);
//        usersListData.postValue(dao.index());
//        // api.add(post);
//    }
//    public User hasUser(String username){
//        return dao.get(username);
//    }
//
////    public List<User> refresh(){
////        return dao.index();
////    }
//
////    public void delete(final Post post) {
////        api.delete(post);
////    }
////
////    public void reload() {
////        api.get();
////    }
//}
