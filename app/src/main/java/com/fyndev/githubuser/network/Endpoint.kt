package com.fyndev.githubuser.network

import com.fyndev.githubuser.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("users")
    suspend fun getUser(): Response<ArrayList<User>>

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): Response<User>
}