package com.example.androidapplication.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.API.ContactsAPI;
import com.example.androidapplication.AppDB;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.R;
import com.example.androidapplication.entities.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private ContactDao dao;
    private ContactsListData contactsListData;
    private AppDB db;
    private String username;
    private String server;
    private ContactsAPI api;

    public String getUsername() {
        return username;
    }

    public ContactsRepository(Context context, String username, String server) {
        this.db = AppDB.getInstance(context);
        this.dao = db.contactDao();
        this.contactsListData = new ContactsListData();
        this.username = username;
        this.server = server;
        this.api = new ContactsAPI(contactsListData, dao, server);
        api.get(this, username);
    }

    class ContactsListData extends MutableLiveData<List<Contact>> {
        public ContactsListData() {
            super();
            List<Contact> contacts = new LinkedList<Contact>();
            setValue(contacts);
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
                List<Contact> a = dao.index(username);
                List<Contact> allList = dao.index(username);
                contactsListData.postValue(dao.index(username));
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

    public void handleAPIData(int status, List<Contact> contacts) {
        if (status == 200) {

            new Thread(() -> {
                this.dao.deleteAll(this.username);
                //this.dao.insertAll(updateContactsFields(contacts));
                for (Contact contact: contacts){
                    contact.setContactOfUser(this.username);
                    contact.setProfileImg(R.drawable.default_profile_image);
                    this.dao.insert(contact);
                }
                this.contactsListData.postValue(contacts);
            }).start();
        }
    }

    private List<Contact> updateContactsFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setContactOfUser(username);
        }
        return contacts;
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

/*    public void reload() {
        api.get(this);
    }*/
}