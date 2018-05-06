package com.backupbro.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import com.backupbro.R
import com.backupbro.network.RetrofitInstance
import com.backupbro.network.UserService

class LoginFragment : Fragment() {
    @BindView(R.id.login_button)
    lateinit var loginButton: Button

    @BindView(R.id.reset_password_page_button)
    lateinit var resetPasswordButton: Button

    @BindView(R.id.signup_page_button)
    lateinit var signupButton: Button

    @BindView(R.id.login_input_email)
    lateinit var emailInputText: EditText

    @BindView(R.id.login_input_password)
    lateinit var passwordInputText: EditText

    private var userService: UserService? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userService = RetrofitInstance.getRetrofitInstance().create(UserService::class.java)
        return inflater?.inflate(R.layout.login_fragment, container, false)
        //return super.onCreateView(inflater, container, savedInstanceState)
    }
}