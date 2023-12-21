package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.FoodData
import com.example.projectuasmobile.data.FoodDataWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodService {
    @POST("foods")
    fun addFood(@Body foodData: FoodDataWrapper): Call<FoodResponse>

    @GET("foods/{id}")
    fun getFoodById(@Path("id") id: Int, @Query("populate") populate: String?): Call<ApiResponse<List<FoodResponse>>>

    @GET("foods")
    fun getAllFood(): Call<ApiResponse<List<FoodResponse>>>
}