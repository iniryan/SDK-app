package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.FoodData
import com.example.projectuasmobile.data.FoodDataWrapper
import com.example.projectuasmobile.data.UpdateData
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.AuthResponse
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodService {
    @POST("foods")
    fun addFood(@Body foodData: FoodDataWrapper): Call<FoodResponse>

    @GET("foods/{id}")
    fun getFoodById(@Path("id") id: Int, @Query("populate") populate: String?): Call<ApiResponse<List<FoodResponse>>>

    @GET("foods")
    fun getAllFood(@Query("filters[booth]") booth: String?, @Query("populate") populate: String?): Call<ApiResponse<List<FoodResponse>>>

    @PUT("foods/{id}")
    fun update(@Path("id") id: String?, @Body foodData: FoodDataWrapper): Call<FoodResponse>

    @DELETE("foods/{id}")
    fun delete(@Path("id") id: Int): Call<FoodResponse>
}
