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

    private var userService: UserService? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.registration_fragment, container, false)
        userService = RetrofitInstance.getRetrofitInstance().create(UserService::class.java)
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
        val user = User()
        user.email = emailInputText.text.toString()
        user.username = usernameInputText.text.toString()
        user.password = passwordInputText.text.toString()
        val userModel = userService?.register(user)
        userModel?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity.applicationContext, "Registration Succeeded", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(activity.applicationContext, "Registration failed: " + response.body().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(activity.applicationContext, "Registration failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loginPage() {
        val fm = activity.supportFragmentManager.beginTransaction()
        fm.replace(R.id.authentication_fragment, LoginFragment(), LoginFragment.TAG)
        fm.commit()
    }

}