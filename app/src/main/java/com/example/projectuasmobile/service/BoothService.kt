package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.BoothDataWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.Booth
import com.example.projectuasmobile.response.BoothResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BoothService {
    @POST("booths")
    fun createBooth(@Body boothData: BoothDataWrapper): Call<Booth>

    @GET("booths")
    fun getAllBooth(): Call<BoothResponse<List<Booth>>>
}
