package com.backupbro.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.backupbro.R
import com.backupbro.activity.LoginActivity
import com.backupbro.model.User
import com.backupbro.network.RetrofitInstance
import com.backupbro.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private var loginPageButton: Button? = null
    private var signupButton: Button? = null
    private var emailInputText: EditText? = null
    private var usernameInputText: EditText? = null
    private var passwordInputText: EditText? = null
    private var userService: UserService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        userService = RetrofitInstance.getRetrofitInstance().create(UserService::class.java)
        loginPageButton = findViewById<View>(R.id.login_page_button) as Button
        signupButton = findViewById<View>(R.id.signup_button) as Button
        emailInputText = findViewById<View>(R.id.registration_input_email) as EditText
        usernameInputText = findViewById<View>(R.id.registration_input_username) as EditText
        passwordInputText = findViewById<View>(R.id.registration_input_password) as EditText

        signupButton?.setOnClickListener {
            register()
        }

        loginPageButton?.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }

    }

    private fun register() {
        val user = User()
        user.email = emailInputText?.text.toString()
        user.username = usernameInputText?.text.toString()
        user.password = passwordInputText?.text.toString()
        val userModel = userService?.register(user)
        userModel?.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>?, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Registration Succeeded", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(applicationContext, "Registration failed: " + response.body().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_LONG).show()
            }
        })
    }
}

//userModel.enqueue(object : Callback<User> {
//    override fun onResponse(call: Call<User>, response: Response<User>) {
//        Toast.makeText(getApplicationContext(), "Login Succeeded", Toast.LENGTH_LONG).show()
//
//    }
//
//    override fun onFailure(call: Call<User>, t: Throwable) {
//        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show()
//
//    }
//})

//request.enqueue(object : Callback<MyModel> {
//    override fun onFailure(call: Call<MyModel>?, t: Throwable?) {
//        //
//    }
//
//    override fun onResponse(call: Call<MyModel>?, response: Response<MyModel>?) {
//        //
//    }