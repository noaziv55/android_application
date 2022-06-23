package com.example.androidapplication.API;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface WebServiceAPI {

    @GET("contacts")
    Call<List<Contact>> getContacts(@Query("username") String username);

    @POST("contacts")
    Call<Void> createContact(@Body Contact contact);

    @GET("users")
    Call <User> getUser(@Query("username") String username ,@Query("password") String password );

    @POST("users")
    Call<Void> createUser(@Body User user );

/*
    @GET("contacts")
    Call<List<Contact>> getContacts(@Header("Authorization") String token);

    @POST("contacts")
    Call<Void> createContact(@Header("Authorization") String token, @Body Contact contactId);

    // @POST("invitations")
    // Call<Void> inviteContact(@Header("Authorization")String token,@Body Invite invite);

    // @POST("transfer")
    // Call<Void> transfer(@Header("Authorization")String token,@Body Transfer transfer);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Header("Authorization") String token, @Path("id") String id);

    @POST("contacts/{id}/messages")
    Call<Void> createMessage(@Header("Authorization") String token, @Path("id") String id, @Body Message message);

    //@POST("api/firebase/onconnect")
    Call<Void> firebaseOnConnect(@Header("Authorization") String token, @Body String firebaseToken);*/
}