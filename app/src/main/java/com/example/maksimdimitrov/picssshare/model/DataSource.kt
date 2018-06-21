package com.example.maksimdimitrov.picssshare.model

import com.example.maksimdimitrov.picssshare.fragments.SignInFragment

object DataSource{

    fun registerUser(user :User) : Boolean {
        return true
    }

    fun checkUsername(username : String): Boolean {
        return true
    }

    fun checkEmail(email : String) : Boolean {
        return true
    }

    fun login(user : SignInFragment.SignInData) : Boolean {
        return true
    }

    fun getUser(username : String) : User {
        return User(username, "12345","$username@gmail.com", DateOfBirth(year = 1995, month = 10, day = 7), true)
    }

}