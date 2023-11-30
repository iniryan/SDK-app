package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.UpdateData
import com.example.projectuasmobile.response.AuthResponse
import com.example.projectuasmobile.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users")
    fun getData(): Call<List<UserResponse>>

    @DELETE("users/{id}")
    fun delete(@Path("id") id: Int): Call<UserResponse>

    @PUT("users/{id}")
    fun update(@Path("id") id: String?, @Body body: UpdateData): Call<AuthResponse>
}