package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.LoginData
import com.example.projectuasmobile.data.RegisterData
import com.example.projectuasmobile.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/local/register")
    fun saveData(@Body body: RegisterData) : Call<AuthResponse>

    @POST("auth/local")
    fun getData(@Body body: LoginData): Call<AuthResponse>
}