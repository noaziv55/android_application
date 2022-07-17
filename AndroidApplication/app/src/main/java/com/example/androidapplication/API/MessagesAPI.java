package com.example.androidapplication.API;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapplication.MessageDao;
import com.example.androidapplication.R;
import com.example.androidapplication.AndroidApplication;
import com.example.androidapplication.entities.Message;
import com.example.androidapplication.entities.Transfer;
import com.example.androidapplication.repositories.MessagesRepository;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessagesAPI {
    private final MutableLiveData<List<Message>> MessagesListData;
    private final MessageDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    String serverUrl;

    public MessagesAPI(MutableLiveData<List<Message>> messagesListData, MessageDao dao) {
        this.MessagesListData = messagesListData;
        this.dao = dao;
        /*if (server.startsWith("localhost")) {
            server = server.replace("localhost", "10.0.2.2");
        }
        serverUrl = "http://" + server + "/api/";*/

        retrofit = new Retrofit.Builder()
                .baseUrl(AndroidApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get(MessagesRepository repository, String id, String username) {
        Call<List<Message>> call = webServiceAPI.getMessages(id, username);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {

                Log.i("success", "succeeded to get messages");
                repository.handleAPIData(response.code(), response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable t) {
                Log.i("failure", "failed to get messages");
            }
        });
    }

    public void transfer(Transfer transfer, MessagesRepository repository, Message message) {
        Call<Void> call = webServiceAPI.transferMessage(transfer);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.i("success", "succeeded to transfer a message");
                repository.postHandle();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("failure", "failed to transfer a message");
            }
        });
    }

    public void post(Message message, MessagesRepository repository, String id) {
        Call<Void> call = webServiceAPI.createMessage(id, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.i("success", "succeeded to post a message");
                repository.afterPost(message);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("failure", "fail to post a message");
            }
        });
    }
}
