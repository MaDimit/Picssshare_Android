package com.example.maksimdimitrov.picssshare.model

import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.fragments.SignInFragment
import com.example.maksimdimitrov.picssshare.utilities.*

object DataSource {

    fun registerUser(user: User): Boolean {
        return true
    }

    fun checkUsername(username: String): Boolean {
        return true
    }

    fun checkEmail(email: String): Boolean {
        return true
    }

    fun login(user: SignInFragment.SignInData): Boolean {
        return true
    }

    fun getUser(username: String) = User(username
            , "12345"
            , "$username@gmail.com"
            , DateOfBirth(year = 1995, month = 10, day = 7)
            , true)

    fun getPosts(username: String) = listOf(Post(IMG1, PROFILE1, username, 23, 212)
            , Post(IMG2, PROFILE5, username, 323, 22)
            , Post(IMG3, PROFILE3, "Vasya", 233, 2612)
            , Post(IMG4, PROFILE4, "Krasi", 223, 212)
            , Post(IMG5, PROFILE5, "Nasoi", 3, 2142)
            , Post(IMG6, PROFILE1, "Nguen", 2354, 2412)
            , Post(IMG7, PROFILE4, "Maxim", 2343, 2122)
            , Post(IMG8, PROFILE3, "Name", 223, 2122)
            , Post(IMG9, PROFILE4, "Noname", 34523, 21232)
            , Post(IMG10, PROFILE1, username, 23, 212)
            , Post(IMG11, PROFILE5, username, 323, 22)
            , Post(IMG12, PROFILE5, "Vasya", 233, 612)
            , Post(IMG13, PROFILE1, "Krasi", 23, 12)
            , Post(IMG14, PROFILE3, "Nasoi", 0, 42)
            , Post(IMG15, PROFILE4, "Nguen", 2354, 12)
            , Post(IMG16, PROFILE5, "Maxim", 343, 22)
            , Post(IMG1, PROFILE1, "Name", 23, 22)
            , Post(IMG2, PROFILE3, "Noname", 3423, 132))
}