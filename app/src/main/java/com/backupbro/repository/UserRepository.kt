package com.backupbro.repository

import com.backupbro.model.User
import com.backupbro.network.RetrofitInstance
import com.backupbro.network.UserService
import io.reactivex.Observable

interface UserRepository {
    fun login(email: String, password: String): Observable<User>
    fun register(email: String, password: String, username: String): Observable<User>

}
class UserRepositoryImpl : BaseRepository(), UserRepository {

    private var userService: UserService = RetrofitInstance.getRetrofitInstance().create(UserService::class.java)

    override fun login(email: String, password: String): Observable<User> {
        var user = User()
        user.email = email
        user.password = password
        return userService.login(user)
    }

    override fun register(email: String, password: String, username: String): Observable<User> {
        var user = User()
        user.email = email
        user.password = password
        user.username = username
        return userService.register(user)
    }

}