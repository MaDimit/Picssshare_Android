package com.example.maksimdimitrov.picssshare.model

import android.os.Parcel
import android.os.Parcelable

data class Post(val image: String, val profilePic: String, val username : String, val views: Int, val comments: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(profilePic)
        parcel.writeString(username)
        parcel.writeInt(views)
        parcel.writeInt(comments)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}