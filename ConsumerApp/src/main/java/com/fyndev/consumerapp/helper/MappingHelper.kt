package com.fyndev.consumerapp.helper

import android.database.Cursor
import com.fyndev.consumerapp.data.User
import com.fyndev.consumerapp.database.DatabaseContract.UserColumn.Companion.AVATAR
import com.fyndev.consumerapp.database.DatabaseContract.UserColumn.Companion.USERNAME
import com.fyndev.consumerapp.database.DatabaseContract.UserColumn.Companion._ID

object MappingHelper {

    fun mapCursorToArrayList(userCursor: Cursor?): ArrayList<User> {
        val userList = ArrayList<User>()

        userCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val imgPhoto = getString(getColumnIndexOrThrow(AVATAR))
                userList.add(User(id = id, login = username, avatar_url = imgPhoto))
            }
        }

        return userList
    }
}