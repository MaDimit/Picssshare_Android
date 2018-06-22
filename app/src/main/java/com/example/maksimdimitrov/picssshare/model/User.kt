package com.example.maksimdimitrov.picssshare.model

import android.os.Parcel
import android.os.Parcelable
import com.example.maksimdimitrov.picssshare.utilities.PROFILE1
import java.text.DateFormatSymbols

data class DateOfBirth(val year: Int, val month: Int, val day: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt())

    override fun toString(): String {
        return "$day ${DateFormatSymbols().months[month]} $year"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(year)
        parcel.writeInt(month)
        parcel.writeInt(day)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DateOfBirth> {
        override fun createFromParcel(parcel: Parcel): DateOfBirth {
            return DateOfBirth(parcel)
        }

        override fun newArray(size: Int): Array<DateOfBirth?> {
            return arrayOfNulls(size)
        }
    }
}

class User(var username: String
           , var password: String
           , var email: String
           , dateOfBirth: DateOfBirth?
           , var isMale: Boolean
           , val profileUserPic: String = PROFILE1) : Parcelable {

    var dateOfBirth = dateOfBirth
        set(value) {
            value?.let {
                if (it.month !in 0..11) {
                    throw Exception("month ${it.month} not in 0 to 11")
                }
                if (it.day !in 0..31) {
                    throw Exception("day ${it.month} not in 0 to 31")
                }
                field = value
            }
        }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            null,
            parcel.readByte() != 0.toByte(),
            parcel.readString()) {
    }


    override fun toString(): String {
        return "User(username=$username, password=$password, email=$email, isMale=$isMale, dateOfBirth=$dateOfBirth)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(email)
        parcel.writeByte(if (isMale) 1 else 0)
        parcel.writeString(profileUserPic)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}