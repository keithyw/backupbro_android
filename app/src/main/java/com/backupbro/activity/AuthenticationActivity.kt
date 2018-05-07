package com.backupbro.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.backupbro.R
import com.backupbro.fragment.LoginFragment

class AuthenticationActivity : AppCompatActivity() {

    private var loginFragment: LoginFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentication_activity)
        if (savedInstanceState == null) {
            loadFragment()
        }
    }

    private fun loadFragment() {
        if (loginFragment == null) {
            loginFragment = LoginFragment()
            supportFragmentManager.beginTransaction().replace(R.id.authentication_fragment, loginFragment, LoginFragment.TAG).commit()
        }
    }
}