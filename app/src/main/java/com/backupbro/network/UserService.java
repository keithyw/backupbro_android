package com.backupbro.network;

import com.backupbro.model.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("email")
    Observable<User> findUserByEmail(@Body User user);
    //Call<User> findUserByEmail(@Body User user);

    @POST("login")
    Observable<User> login(@Body User user);
    //Call<User> login(@Body User user);

    @POST("register")
    Observable<User> register(@Body User user);
    //Call<User> register(@Body User user);

}
