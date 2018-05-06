package com.backupbro.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.backupbro.model.User;
import com.backupbro.network.RetrofitInstance;
import com.backupbro.network.UserService;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepository extends BaseRepository {
    private UserService userService;

    public UserRepository() {
        this.userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
    }

    public LiveData<User> login(String email, String password) {
        User user  = new User();
        user.setEmail(email);
        user.setPassword(password);
        final MutableLiveData<User> data = new MutableLiveData<>();
        this.userService.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;
    }

    public LiveData<User> register(User user) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        this.userService.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;
    }

    public LiveData<User> findByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        final MutableLiveData<User> data = new MutableLiveData<>();
        this.userService.findUserByEmail(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;
    }
}
