package com.backupbro.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.backupbro.repository.UserRepository;
import javax.inject.Inject;
import io.reactivex.Observable;

public class UserViewModel extends ViewModel {
    private LiveData<User> user;
    private UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public void login(String email, String password) {
//        this.user = this.userRepository.login(email, password);
//    }
//
//    public LiveData<User> getUser() {
//        return user;
//    }

//
}
