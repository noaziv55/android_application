package com.example.androidapplication.API;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.ContactDao;
import com.example.androidapplication.AndroidApplication;
import com.example.androidapplication.R;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.repositories.ContactsRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsAPI {
    private final MutableLiveData<List<Contact>> contactListData;
    private final ContactDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    String serverUrl;

    public ContactsAPI(MutableLiveData<List<Contact>> contactListData, ContactDao dao, String server) {
        this.contactListData = contactListData;
        this.dao = dao;
        if (server.startsWith("localhost")) {
            server = server.replace("localhost", "10.0.2.2");
        }
        serverUrl = "http://" + server + "/api/";

        retrofit = new Retrofit.Builder()
                .baseUrl(AndroidApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get(ContactsRepository repository, String username) {
        Call<List<Contact>> call = webServiceAPI.getContacts(username);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {

                Log.i("success", "success get action");
                //List<Contact> contacts1 = response.body();
                //contacts.postValue(contacts1);
                repository.handleAPIData(response.code(), response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {
                Log.i("fail", "fail");
            }
        });
    }
}