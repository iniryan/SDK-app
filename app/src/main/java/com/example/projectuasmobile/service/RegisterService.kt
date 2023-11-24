package com.example.projectuasmobile

import com.example.projectuasmobile.data.RegisterData
import com.example.projectuasmobile.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
interface RegisterService {
    @POST("auth/local/register")
    fun saveData(@Body body: RegisterData) : Call<LoginResponse>
}