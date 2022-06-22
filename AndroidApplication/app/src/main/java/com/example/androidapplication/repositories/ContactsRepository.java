package com.example.androidapplication.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.AppDB;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.entities.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private ContactDao dao;
    private ContactsListData contactsListData;
    private AppDB db;
    private String username;
    //private PostAPI api;

    public ContactsRepository(Context context, String username) {
        this.db = AppDB.getInstance(context);
        this.dao = db.contactDao();
        this.contactsListData = new ContactsListData();
        this.username =username;
    }

    class ContactsListData extends MutableLiveData<List<Contact>> {
        public ContactsListData() {
            super();
            setValue(new LinkedList<Contact>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                contactsListData.postValue(dao.index(username));
            }).start();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactsListData;
    }

   public void add(final Contact contact) {
       dao.insert(contact);
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
