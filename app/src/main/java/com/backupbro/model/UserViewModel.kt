package com.backupbro.model

import android.arch.lifecycle.ViewModel
import com.backupbro.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserViewModel @Inject constructor(
        val userRepository: UserRepository
    ): ViewModel() {

    lateinit var user : Observable<User>

    fun login(email: String, password: String) : Observable<User> {
        this.user = userRepository.login(email, password)
        return this.user
    }

    fun register(email: String, password: String, username: String) : Observable<User> {
        this.user = userRepository.register(email, password, username)
        return this.user
    }


}