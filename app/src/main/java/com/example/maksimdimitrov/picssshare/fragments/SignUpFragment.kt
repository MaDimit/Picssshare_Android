package com.example.maksimdimitrov.picssshare.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.model.DateOfBirth
import com.example.maksimdimitrov.picssshare.model.User
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import java.util.*

class SignUpFragment : Fragment() {

    interface SignUpListener {
        fun onSignUpClicked(user: User): Boolean
    }

    private lateinit var listener: SignUpListener
    private lateinit var rootView: View
    private val user = User("", "", "", null, false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_sign_up, container, false)
        setListeners()
        retainInstance = true
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SignUpListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement SignUpRegisterListener")
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        user.dateOfBirth?.let {
            btn_date.apply {
                text = it.toString()
                setBackgroundResource(R.drawable.active_button)
            }
        }
        Log.d("ISMALE", "isMale ${user.isMale}")
        female_btn.isChecked = !user.isMale
        male_btn.isChecked = user.isMale
    }

    private fun setListeners() {
        val dateBtn = rootView.btn_date
        val maleBtn = rootView.male_btn
        val femaleBtn = rootView.female_btn
        val signUpBtn = rootView.btn_signup
        val vUsername = rootView.signup_username
        val vPassword = rootView.signup_password
        val vRepeatPassword = rootView.signup_repeat_password
        val vEmail = rootView.signup_email

        fun setDateListener() {
            // When dialog closed
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val dateOfBirth = DateOfBirth(year, month, day)
                user.dateOfBirth = dateOfBirth
                Log.d("LOG", "date is set, date is ${user.dateOfBirth}")
                dateBtn.setBackgroundResource(R.drawable.active_button)
                dateBtn.text = dateOfBirth.toString()
            }

            // Date of birth btn
            dateBtn.setOnClickListener {
                val cal = Calendar.getInstance()
                val year = user.dateOfBirth?.year ?: cal.get(Calendar.YEAR)
                val month = user.dateOfBirth?.month ?: cal.get(Calendar.MONTH)
                val day = user.dateOfBirth?.day ?: cal.get(Calendar.DAY_OF_MONTH)

                val datePicker = DatePickerDialog(
                        activity,
                        R.style.DatePicker,
                        listener,
                        year, month, day)
                datePicker.window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                datePicker.datePicker.maxDate = Date().time
                datePicker.show()
            }
        }

        fun setGenderListener() {
            val onClick = { btn1: ToggleButton, btn2: ToggleButton ->
                btn1.isChecked = true
                btn2.isChecked = false
            }
            maleBtn.setOnClickListener {
                onClick(maleBtn, femaleBtn)
                user.isMale = true
                Log.d("GENDER", "male button clicked. male:${maleBtn.isChecked}, female:${femaleBtn.isChecked}")
            }
            femaleBtn.setOnClickListener {
                onClick(femaleBtn, maleBtn)
                user.isMale = false
                Log.d("GENDER", "female button clicked. male:${maleBtn.isChecked}, female:${femaleBtn.isChecked}")
            }
        }

        fun View.setError(err: String, ifStatement: () -> Boolean) = ifStatement().also {
            if(it) {
                if (this is EditText) {
                    Log.d("LOG", "I'm in edit text if")
                    error = err
                } else if (this is Button) {
                    Log.d("LOG", "I'm in button if")
                    text = err
                }
            }
        }

        fun validateData(username: String, password: String, repeatPassword: String, email: String, date: DateOfBirth?): Boolean {
            val requiredStr = getString(R.string.err_required)
            Log.d("LOG", "date btn is $date")
            return (vUsername.setError(requiredStr) { username.isEmpty() }
                    or vRepeatPassword.setError(getString(R.string.err_pass)) { password != repeatPassword }
                    or vPassword.setError(requiredStr) { password.isEmpty() }
                    or vRepeatPassword.setError(requiredStr) { repeatPassword.isEmpty() }
                    or vEmail.setError(getString(R.string.err_email)) { !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() }
                    or vEmail.setError(requiredStr) { email.isEmpty() }
                    or dateBtn.setError(requiredStr) { date == null })
        }


        fun setSignUpListener() {
            signUpBtn.setOnClickListener {
                val username = vUsername.text.toString()
                val password = vPassword.text.toString()
                val repeatPassword = vRepeatPassword.text.toString()
                val email = vEmail.text.toString()
                val isMale = maleBtn.isChecked
                val date: DateOfBirth? = user.dateOfBirth

                if (!validateData(username, password, repeatPassword, email, date)) {
                    user.username = username
                    user.password = password
                    user.email = email
                    user.isMale = isMale

                    val success = listener.onSignUpClicked(user)
                    if (!success) {
                        vUsername.error = getString(R.string.err_username_exists)
                    }
                }
            }
        }

        var signUpActivated = false
        fun setBtnColorChanger() {
            val action = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (vUsername.text.isNotEmpty()
                            && vPassword.text.isNotEmpty()
                            && vRepeatPassword.text.isNotEmpty()
                            && vEmail.text.isNotEmpty()
                            && dateBtn.text != getString(R.string.date_of_birth)
                            && dateBtn.text != getString(R.string.err_required)
                    ) {
                        if (!signUpActivated) {
                            signUpBtn.setBackgroundResource(R.drawable.active_button)
                            signUpActivated = true
                        }
                    } else {
                        if (signUpActivated) {
                            signUpBtn.setBackgroundResource(R.drawable.inactive_button)
                            signUpActivated = false
                        }
                    }
                }
            }
            vUsername.addTextChangedListener(action)
            vPassword.addTextChangedListener(action)
            vRepeatPassword.addTextChangedListener(action)
            vEmail.addTextChangedListener(action)
            dateBtn.addTextChangedListener(action)
        }

        setDateListener()
        setGenderListener()
        setSignUpListener()
        setBtnColorChanger()
    }
}
