package com.backupbro.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.backupbro.R

class ForgotPasswordFragment : Fragment() {
    companion object {
        val TAG = ForgotPasswordFragment::class.java.simpleName
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.forgot_password_fragment, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

    }
}