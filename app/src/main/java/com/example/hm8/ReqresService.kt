package com.example.hm8

import com.example.hm8.ReqresData
import com.example.hm8.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresService {

    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<ReqresData<List<User>>>

    @GET("users/{userId}")
    fun getUserInfo(@Path("userId") userId: Long): Call<ReqresData<User>>

}