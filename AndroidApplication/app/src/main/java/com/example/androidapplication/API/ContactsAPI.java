package com.example.androidapplication.API;

import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.entities.Contact;

import java.util.List;

import retrofit2.Retrofit;

public class ContactsAPI {
    private MutableLiveData<List<Contact>> contactListData;
    WebServiceAPI webServiceAPI;
    Retrofit retrofit;
}
