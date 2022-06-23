package com.example.androidapplication.API;

import com.example.androidapplication.entities.Contact;
import com.example.androidapplication.entities.Invite;
import com.example.androidapplication.entities.Message;
//import com.example.androidapplication.entities.Transfer;
import com.example.androidapplication.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {

    class PostContact {
        public String username;
        public String contactName;
        public String contactNickname;
        public String contactServer;

        public void setUsername(String username) {
            this.username = username;
        }

        public PostContact(String username, String contactName, String contactNickname, String contactServer) {
            this.username = username;
            this.contactName = contactName;
            this.contactNickname = contactNickname;
            this.contactServer = contactServer;
        }
        public void setPostContactOfUser(String username) {
            this.username = username;
        }

    }

    @GET("contacts")
    Call<List<Contact>> getContacts(@Query("username") String username);

    @POST("contacts")
    Call<Void> createContact(@Body PostContact contact);

    @POST("invitations")
    Call<Invite> inviteContact(@Body Invite invite);

    @GET("users")
    Call <User> getUser(@Query("username") String username ,@Query("password") String password );

    @POST("users")
    Call<Void> createUser(@Body User user );

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Path("id") String id, @Query("username") String username);

    @GET("contacts/{id}/messages")
    Call<Void> createMessage(@Path("id") String id, @Body Message message);

//    @POST("transfer")
//    Call<Void> transferMessage(@Body Transfer transfer);


/*@POST("api/firebase/onconnect")
    Call<Void> firebaseOnConnect(@Body String firebaseToken);*/
}