package com.backupbro.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.backupbro.R
import com.backupbro.model.UserViewModel
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.architecture.ext.viewModel

class RegistrationFragment : Fragment() {
    companion object {
        val TAG = RegistrationFragment::class.java.simpleName
    }

    @BindView(R.id.login_page_button)
    lateinit var loginPageButton: Button

    @BindView(R.id.signup_button)
    lateinit var signupButton: Button

    @BindView(R.id.registration_input_email)
    lateinit var emailInputText: EditText

    @BindView(R.id.registration_input_username)
    lateinit var usernameInputText: EditText

    @BindView(R.id.registration_input_password)
    lateinit var passwordInputText: EditText

    val userViewModel by viewModel<UserViewModel>()

    lateinit var validator: AwesomeValidation

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initValidation()
    }

    private fun initValidation() {
        validator = AwesomeValidation(ValidationStyle.BASIC)
        val regexPassword = "^(([a-zA-Z]+\\d+)|(\\d+[a-zA-Z]+))[a-zA-Z0-9]*\$"
        val regexUsername = "^(\\w){3,50}\$"
        validator.addValidation(activity, R.id.registration_input_email, android.util.Patterns.EMAIL_ADDRESS, R.string.error_invalid_email)
        validator.addValidation(activity, R.id.registration_input_password, regexPassword, R.string.error_invalid_password)
        validator.addValidation(activity, R.id.registration_input_username, regexUsername, R.string.error_invalid_username)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.registration_fragment, container, false)
        ButterKnife.bind(this, view)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        loginPageButton.setOnClickListener {
            loginPage()
        }

        signupButton.setOnClickListener {
            register()
        }
    }

    private fun register() {
        if (validator.validate()) {
            handleRegistration()
        }
    }

    private fun handleRegistration() {
        userViewModel.register(emailInputText.text.toString(), passwordInputText.text.toString(), usernameInputText.text.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ user ->
                Toast.makeText(activity?.applicationContext, "Registration Success for " +user.username, Toast.LENGTH_LONG).show()
            },
            { error ->
                Toast.makeText(activity?.applicationContext, "Registration Failed " + error.message, Toast.LENGTH_LONG).show()
            })
    }

    private fun loginPage() {
        val fm = activity?.supportFragmentManager?.beginTransaction()
        fm?.replace(R.id.authentication_fragment, LoginFragment(), LoginFragment.TAG)
        fm?.commit()
    }

}