package com.example.androidapplication.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.repositories.ContactsRepository;

import java.util.List;

public class ContactsViewModel {

    private ContactsRepository repository;
    private LiveData<List<Contact>> contacts;
    private String username;

    public ContactsViewModel(Context context, String username){
        this.repository = new ContactsRepository(context, username);
        this.contacts= repository.getAll();
        this.username=username;
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public String getUsername(){
        return username;
    }

    //not good
//    public List<Contact> refresh(){
//        return this.repository.refresh();
//    }

    public void add(Contact contact) {
        repository.add(contact); }

//    public void delete(Contact contact) { repository.delete(contact); }
//    public void reload() { repository.reload(); }
}