package com.example.projectuasmobile.service

import com.example.projectuasmobile.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("users")
    fun getData(): Call<List<UserResponse>>

    @GET("users/{id}")
    fun getDataById(@Path("id") id: Int, @Query("populate") populate: String?): Call<UserResponse>
}