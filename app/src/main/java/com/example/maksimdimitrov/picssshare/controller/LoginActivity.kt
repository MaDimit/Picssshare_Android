package com.example.maksimdimitrov.picssshare.controller

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.fragments.SignInFragment
import com.example.maksimdimitrov.picssshare.fragments.SignUpFragment
import com.example.maksimdimitrov.picssshare.model.DataSource
import com.example.maksimdimitrov.picssshare.model.User
import com.example.maksimdimitrov.picssshare.utilities.CARD_ELEVATION_DEFAULT
import com.example.maksimdimitrov.picssshare.utilities.EXTRA_USER
import com.example.maksimdimitrov.picssshare.utilities.whenNull
import kotlinx.android.synthetic.main.activity_login.*

const val SIGN_IN_FRAGMENT = "sign_in_fragment"
const val SIGN_UP_FRAGMENT = "sign_up_fragment"


class LoginActivity : AppCompatActivity(), SignInFragment.SignInListener, SignUpFragment.SignUpListener {

    override fun onSignInClicked(signInData: SignInFragment.SignInData) : Boolean {
        return DataSource.login(signInData).also {
            if(it){
                val user = DataSource.getUser(signInData.username)
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(EXTRA_USER, user)
                }
                startActivity(intent)
            }
        }
    }

    override fun onSignUpNowClicked() {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                .replace(login_card.id, SignUpFragment(), SIGN_UP_FRAGMENT)
                .addToBackStack("sign_up")
                .commit()
    }

    override fun onSignUpClicked(user: User) : Boolean {
        return DataSource.registerUser(user).also{
            if(it) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(EXTRA_USER, user)
                }
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        savedInstanceState.whenNull{
            supportFragmentManager.beginTransaction()
                    .replace(login_card.id, SignInFragment(), SIGN_IN_FRAGMENT)
                    .commit()
        }
        setFlipElevationAnimation()
    }

    private fun setFlipElevationAnimation() {
        supportFragmentManager.addOnBackStackChangedListener {
            login_card.cardElevation = 0F
            val animator = ObjectAnimator.ofFloat(login_card, "cardElevation", CARD_ELEVATION_DEFAULT)
            animator.duration = resources.getInteger(R.integer.card_flip_time_half).toLong() * 8
            animator.start()
        }
    }

}
