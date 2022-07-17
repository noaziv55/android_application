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

    public ContactsViewModel(Context context, String username, String server){
        this.repository = new ContactsRepository(context, username, server);
        this.contacts= repository.getAll();
        this.username=username;
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public String getUsername(){
        return username;
    }

    public Contact getContact(String ContactName, String username){
        return repository.getContact(ContactName,username);
    }
    public void updateContact(Contact contact) {
        repository.updateContact(contact);
    }


    public void add(Contact contact) {
        repository.add(contact); }

    public void refresh() {
        this.repository.refresh();
    }

//    public void delete(Contact contact) { repository.delete(contact); }
    // public void reload() { repository.reload(); }
}