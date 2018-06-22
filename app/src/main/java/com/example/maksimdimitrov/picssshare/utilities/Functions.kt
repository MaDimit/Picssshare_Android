package com.example.maksimdimitrov.picssshare.utilities

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

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

fun ImageView.loadImage(url: String, context: Context) {
    Glide.with(context)
            .load(url)
            .into(this)
}

