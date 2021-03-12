package com.fyndev.githubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyndev.githubuser.data.User
import com.fyndev.githubuser.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val listUser = MutableLiveData<ArrayList<User>>()
    private val filterUser = MutableLiveData<ArrayList<User>>()

    fun setUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getUser()
            if (response.isSuccessful) {
                listUser.postValue(response.body())
            }
        }
    }

    fun getUser(): LiveData<ArrayList<User>> = listUser

    fun setFilter(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.searchUser(login)
            if (response.isSuccessful) {
                filterUser.postValue(response.body()?.items)
            }
        }
    }

    fun getFilter(): LiveData<ArrayList<User>> = filterUser
}