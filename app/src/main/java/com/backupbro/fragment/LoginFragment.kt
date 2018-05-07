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
import com.backupbro.model.User
import com.backupbro.network.RetrofitInstance
import com.backupbro.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private var userService: UserService? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userService = RetrofitInstance.getRetrofitInstance().create(UserService::class.java)
        val view: View = inflater!!.inflate(R.layout.login_fragment, container, false)
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
        val user = User()
        user.email = emailInputText.text.toString()
        user.password = passwordInputText.text.toString()
        val userModel = userService?.login(user)
        userModel?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity.applicationContext, "Login Succeeded", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity.applicationContext, "Login Failed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(activity.applicationContext, "Login Failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun resetPasswordPage() {
        val fm = activity.supportFragmentManager.beginTransaction()
        fm.replace(R.id.authentication_fragment, ForgotPasswordFragment(), ForgotPasswordFragment.TAG)
        fm.commit()
    }

    private fun registerPage() {
        val fm = activity.supportFragmentManager.beginTransaction()
        fm.replace(R.id.authentication_fragment, RegistrationFragment(), RegistrationFragment.TAG)
        fm.commit()
    }
}