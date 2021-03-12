package com.fyndev.githubuser.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
        val login: String = "",
        val name: String = "",
        val avatar_url: String = "",
        val followers: String = "",
        val following: String = "",
        val public_repos: String = "",
        val location: String = "",
) : Parcelable
