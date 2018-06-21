package com.example.maksimdimitrov.picssshare.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.maksimdimitrov.picssshare.R
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

class SignInFragment : Fragment() {

    data class SignInData(val username: String, val password: String)

    interface SignInListener {
        fun onSignUpNowClicked()
        fun onSignInClicked(signInData: SignInData) : Boolean
    }

    private lateinit var listener: SignInListener
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_sign_in, container, false)
        setBtnListeners()
        retainInstance = true
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SignInListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement SignUpRegisterListener")
        }
    }


    private fun setBtnListeners() {
        val vUsername = rootView.signin_username
        val vPassword = rootView.signin_password
        val signInBtn = rootView.btn_signin

        rootView.signin_signup_txt.setOnClickListener {
            listener.onSignUpNowClicked()
        }

        signInBtn.setOnClickListener {
            val signInData = SignInData(vUsername.text.toString(), vPassword.text.toString())
            if(signInData.username.isEmpty()) {
                vUsername.error = getString(R.string.err_required)
                return@setOnClickListener
            }
            if(signInData.password.isEmpty()) {
                vPassword.error = getString(R.string.err_required)
                return@setOnClickListener
            }
            val success = listener.onSignInClicked(signInData)
            if(!success){
                vPassword.error = "Wrong username or password"
            }
        }

        var signUpActivated = false
        fun setBtnColorChanger() {
            val action = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (vUsername.text.isNotEmpty() && vPassword.text.isNotEmpty()) {
                        if (!signUpActivated) {
                            signInBtn.setBackgroundResource(R.drawable.active_button)
                            signUpActivated = true
                        }
                    } else {
                        if (signUpActivated) {
                            signInBtn.setBackgroundResource(R.drawable.inactive_button)
                            signUpActivated = false
                        }
                    }
                }
            }
            vUsername.addTextChangedListener(action)
            vPassword.addTextChangedListener(action)
        }
        setBtnColorChanger()
    }

}
