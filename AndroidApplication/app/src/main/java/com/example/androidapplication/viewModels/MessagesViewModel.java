package com.example.androidapplication.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Message;
import com.example.androidapplication.repositories.ContactsRepository;
import com.example.androidapplication.repositories.MessagesRepository;

import java.util.List;

public class MessagesViewModel {
    private MessagesRepository repository;
    private LiveData<List<Message>> messages;
    private String username;

    public MessagesViewModel(Context context, String username, String contactName){
        this.repository = new MessagesRepository(context,username, contactName);
        this.messages= repository.getAll();
        this.username=username;
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public String getUsername(){
        return username;
    }

    //not good
//    public List<Contact> refresh(){
//        return this.repository.refresh();
//    }

    public void add(Message message) {
        repository.add(message); }

//    public void delete(Contact contact) { repository.delete(contact); }
//    public void reload() { repository.reload(); }
}