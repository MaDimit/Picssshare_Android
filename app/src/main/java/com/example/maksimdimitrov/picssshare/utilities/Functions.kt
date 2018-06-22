package com.example.maksimdimitrov.picssshare.utilities

import android.content.Context
import android.view.View
import android.widget.Toast

fun Any?.whenNull(func: () -> Unit) {
    if(this == null) {
        func()
    }
}

fun Any?.whenNotNull(func: () -> Unit) {
    if(this != null) {
        func()
    }
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

