package com.fyndev.githubuser.network

import com.fyndev.githubuser.data.User
import com.fyndev.githubuser.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("users")
    suspend fun getUser(): Response<ArrayList<User>>

    @GET("search/users")
    suspend fun searchUser(@Query("q") username: String): Response<UserResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): Response<User>
}