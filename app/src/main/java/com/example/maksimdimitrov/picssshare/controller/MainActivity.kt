package com.example.maksimdimitrov.picssshare.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.model.User
import com.example.maksimdimitrov.picssshare.utilities.EXTRA_USER
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = intent.getParcelableExtra(EXTRA_USER)
        hi.text = "Hi, $user"
    }
}
