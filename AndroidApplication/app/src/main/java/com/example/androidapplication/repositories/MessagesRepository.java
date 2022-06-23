package com.example.androidapplication.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.API.ContactsAPI;
import com.example.androidapplication.API.MessagesAPI;
import com.example.androidapplication.AppDB;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.MessageDao;
import com.example.androidapplication.R;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Message;
import com.example.androidapplication.entities.Transfer;

import java.util.LinkedList;
import java.util.List;

public class MessagesRepository {
    private MessageDao dao;
    private MessagesListData messagesListData;
    private AppDB db;
    private MessagesAPI api;
    private String username;
    private String contactName;

    public MessagesRepository(Context context, String username, String contactName) {
        this.db = AppDB.getInstance(context);
        this.dao = db.messageDao();
        this.messagesListData = new MessagesListData();
        this.username = username;
        this.contactName = contactName;
        this.api = new MessagesAPI(messagesListData, dao);
        api.get(this, this.contactName, this.username);
    }

    public void handleAPIData(int status, List<Message> messages) {
        if (status == 200) {
            new Thread(() -> {
                this.dao.deleteAll(this.username, this.contactName);
                for (Message message : messages) {
                    message.setFrom(this.username);
                    message.setTo(this.contactName);
                    this.dao.insert(message);
                }
                this.messagesListData.postValue(messages);
            }).start();
        }
    }

    public void postHandle(Message message) {
        api.get(this, this.contactName, this.username);
    }

    public void afterPost(Message message) {
        Transfer transfer = new Transfer(this.username, this.contactName, message.getContent());
        new MessagesAPI(this.messagesListData, this.dao).transfer(transfer, this, message);
    }

    class MessagesListData extends MutableLiveData<List<Message>> {
        public MessagesListData() {
            super();
            List<Message> messages = new LinkedList<Message>();
            setValue(messages);
        }

        class PrimeThread extends Thread {
            public void run() {
                messagesListData.postValue(dao.index(username, contactName));
            }
        }

        @Override
        protected void onActive() {
            super.onActive();
            MessagesRepository.MessagesListData.PrimeThread t = new MessagesRepository.MessagesListData.PrimeThread();
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public LiveData<List<Message>> getAll() {
        return messagesListData;
    }

    public void add(final Message message) {
        api.post(message, this, this.contactName);
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