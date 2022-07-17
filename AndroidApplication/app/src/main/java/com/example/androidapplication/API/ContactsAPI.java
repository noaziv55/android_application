package com.example.androidapplication.API;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.AddContactActivity;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.AndroidApplication;
import com.example.androidapplication.R;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Invite;
import com.example.androidapplication.repositories.ContactsRepository;

import java.util.List;
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
        if(server.startsWith("localhost")){
            server = server.replace("localhost","10.0.2.2");
        }
        serverUrl = "http://"+server+"/api/";

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

                Log.i("success", "success get contacts");
                //List<Contact> contacts1 = response.body();
                //contacts.postValue(contacts1);
                repository.handleAPIData(response.code(), response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {
                Log.i("fail", "fail get contacts");
            }
        });
    }

    public void inviteContact(Invite invite, ContactsRepository repository, Contact contact) {
        Call<Invite> call = webServiceAPI.inviteContact(invite);
        call.enqueue(new Callback<Invite>() {
            @Override
            public void onResponse(@NonNull Call<Invite> call, @NonNull Response<Invite> response) {
                Log.i("success", "success invite");
                repository.afterInvite(contact, response.code());
            }

            @Override
            public void onFailure(@NonNull Call<Invite> call, @NonNull Throwable t) {
                Log.i("fail", "fail invite");
            }
        });


    }

    public void post(Contact contact, ContactsRepository repository) {
        Call<Void> call = webServiceAPI.createContact (contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.i("success", "success post contact");
                repository.postHandle();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("fail", "fail post contact");
            }
        });

    }
}
