package com.example.androidapplication.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.AppDB;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.MessageDao;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Message;

import java.util.LinkedList;
import java.util.List;

public class MessagesRepository {
    private MessageDao dao;
    private MessagesListData messagesListData;
    private AppDB db;
    private String username;
    private String contactName;
    //private PostAPI api;

    public MessagesRepository(Context context, String username,String contactName) {
        this.db = AppDB.getInstance(context);
        this.dao = db.messageDao();
        this.messagesListData = new MessagesListData();
        this.username= username;
        this.contactName= contactName;
    }

    class MessagesListData extends MutableLiveData<List<Message>> {
        public MessagesListData() {
            super();
            setValue(new LinkedList<Message>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                messagesListData.postValue(dao.index(username,contactName));
            }).start();
        }
    }

    public LiveData<List<Message>> getAll() {
        return messagesListData;
    }

    public void add(final Message message) {
        dao.insert(message);
        this.messagesListData.postValue(dao.index(username,contactName));

        // api.add(post);
    }

//    public List<Contact> refresh(){
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

