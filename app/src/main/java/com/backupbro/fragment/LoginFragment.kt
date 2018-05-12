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
import io.reactivex.schedulers.Schedulers
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.architecture.ext.viewModel


class LoginFragment : Fragment() {

    companion object {
        val TAG = LoginFragment::class.java.simpleName
    }

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

    val userViewModel by viewModel<UserViewModel>()

    private lateinit var validator: AwesomeValidation

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initValidation()
    }

    private fun initValidation() {
        validator = AwesomeValidation(ValidationStyle.BASIC)
        val regexPassword = "^(([a-zA-Z]+\\d+)|(\\d+[a-zA-Z]+))[a-zA-Z0-9]*\$"
        validator.addValidation(activity, R.id.login_input_email, android.util.Patterns.EMAIL_ADDRESS, R.string.error_invalid_email)
        validator.addValidation(activity, R.id.login_input_password, regexPassword, R.string.error_invalid_password)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.login_fragment, container, false)
        ButterKnife.bind(this, view)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        loginButton.setOnClickListener {
            login()
        }

        resetPasswordButton.setOnClickListener {
            resetPasswordPage()
        }

        signupButton.setOnClickListener {
            registerPage()
        }
    }

    private fun login() {
        if (validator.validate()) {
            handleLogin()
        }
    }

    private fun handleLogin() {
        userViewModel.login(emailInputText.text.toString(), passwordInputText.text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ user ->
                    Toast.makeText(activity?.applicationContext, "Login Succeeded for" + user.username, Toast.LENGTH_LONG).show()
                }, { error ->
                    Toast.makeText(activity?.applicationContext, "Login Failed " + error.message, Toast.LENGTH_LONG).show()
                })
    }

    private fun resetPasswordPage() {
        val fm = activity?.supportFragmentManager?.beginTransaction()
        fm?.replace(R.id.authentication_fragment, ForgotPasswordFragment(), ForgotPasswordFragment.TAG)
        fm?.commit()
    }

    private fun registerPage() {
        val fm = activity?.supportFragmentManager?.beginTransaction()
        fm?.replace(R.id.authentication_fragment, RegistrationFragment(), RegistrationFragment.TAG)
        fm?.commit()
    }
}