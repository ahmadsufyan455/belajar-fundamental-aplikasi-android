package com.fyndev.githubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyndev.githubuser.data.User
import com.fyndev.githubuser.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FollowingViewModel : ViewModel() {
    private val listFollowing = MutableLiveData<ArrayList<User>>()

    fun setData(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getFollowing(login)
            if (response.isSuccessful) {
                listFollowing.postValue(response.body())
            }
        }
    }

    fun getData(): LiveData<ArrayList<User>> = listFollowing
}