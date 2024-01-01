package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.FoodDataWrapper
import com.example.projectuasmobile.data.OrderData
import com.example.projectuasmobile.data.OrderDataWrapper
import com.example.projectuasmobile.data.OrderDetailsDataWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.response.OrderDetailsResponse
import com.example.projectuasmobile.response.OrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderService {
    @POST("orders")
    fun addOrder(@Body orderData: OrderDataWrapper): Call<ApiResponse<OrderResponse>>

    @GET("orders/{id}")
    fun getOrderById(
        @Path("id") id: Int,
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<OrderResponse>>>

    @GET("orders")
    fun getAllOrder(
//        @Query("filters[booth]") booth: String?,
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<OrderResponse>>>

    @PUT("orders/{id}")
    fun update(@Path("id") id: String?, @Body foodData: OrderDataWrapper): Call<ApiResponse<OrderResponse>>

    @DELETE("orders/{id}")
    fun delete(@Path("id") id: Int): Call<OrderResponse>
}

interface OrderDetailsService {
    @POST("order-details")
    fun addOrderDetails(@Body orderData: OrderDetailsDataWrapper): Call<ApiResponse<OrderDetailsResponse>>

    @GET("order-details/{id}")
    fun getOrderDetailsById(
        @Path("id") id: Int,
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<OrderDetailsResponse>>>

    @GET("order-details")
    fun getAllOrderDetails(
        @Query("filters[booth]") booth: String?,
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<OrderDetailsResponse>>>

    @PUT("order-details/{id}")
    fun update(
        @Path("id") id: String?,
        @Body foodData: OrderDataWrapper
    ): Call<OrderDetailsResponse>

    @DELETE("order-details/{id}")
    fun delete(@Path("id") id: Int): Call<OrderDetailsResponse>
}