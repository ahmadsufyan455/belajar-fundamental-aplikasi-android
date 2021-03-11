package com.fyndev.githubuser.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
        val userName: String = "",
        val name: String = "",
        val photo: String = "",
        val follower: String = "",
        val following: String = "",
        val repository: String = "",
        val location: String = "",
        val company: String = ""
) : Parcelable
