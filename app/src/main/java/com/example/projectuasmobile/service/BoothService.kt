package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.BoothDataWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.BoothResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BoothService {
    @POST("booths")
    fun createBooth(@Body boothData: BoothDataWrapper): Call<BoothResponse>

    @GET("booths")
    fun getAllBooth(@Query("filters[boothName][\$contains]") search: String?, @Query("populate") populate: String?): Call<ApiResponse<List<BoothResponse>>>

    @GET("booths/{id}")
    fun getBoothById(@Query("id") id: Int): Call<ApiResponse<BoothResponse>>
}

