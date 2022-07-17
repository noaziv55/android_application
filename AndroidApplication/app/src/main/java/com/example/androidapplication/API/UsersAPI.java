package com.example.androidapplication.API;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.AndroidApplication;
import com.example.androidapplication.ContactDao;
import com.example.androidapplication.R;
import com.example.androidapplication.UserDao;
import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;
import com.example.androidapplication.repositories.ContactsRepository;
import com.example.androidapplication.repositories.UsersRepository;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersAPI {
    private final MutableLiveData<List<User>> usersListData;
    private final UserDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    String serverUrl;

    public UsersAPI(MutableLiveData<List<User>> usersListData, UserDao dao, String server) {
        this.usersListData = usersListData;
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

//    public void get(UsersRepository repository, String username, String password, String server) {
//        Call<List<User>>call = webServiceAPI.getUser(username, password);
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
//
//                Log.i("success", "success get action");
//                //List<Contact> contacts1 = response.body();
//                //contacts.postValue(contacts1);
//                repository.handleAPIDataUsers(response.code(), response.body());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
//                Log.i("fail", "fail");
//            }
//        });
//    }

    public void post(UsersRepository repository, User user) {
        Call<Void> call = webServiceAPI.createUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.i("success", "success get action");
                repository.postHandle(user,response.code());
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("fail", "fail");
            }
        });
    }
}