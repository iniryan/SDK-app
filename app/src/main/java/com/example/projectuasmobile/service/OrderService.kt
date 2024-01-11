package com.example.projectuasmobile.service

import com.example.projectuasmobile.data.OrderDataWrapper
import com.example.projectuasmobile.data.OrderDetailsDataWrapper
import com.example.projectuasmobile.data.RefuseOrderWrapper
import com.example.projectuasmobile.data.UpdateStatusWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.response.OrderDetailsResponse
import com.example.projectuasmobile.response.OrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderService {
    @POST("orders")
    fun addOrder(@Body orderData: OrderDataWrapper): Call<ApiResponse<OrderResponse>>

    @GET("orders")
    fun getOrderById(
        @Query("filters[id]") id: String?,
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<OrderResponse>>>

    @PUT("orders/{id}")
    fun updateStatusOrder(
        @Path("id") id: String?,
        @Body status: UpdateStatusWrapper
    ): Call<ApiResponse<OrderResponse>>

    @PUT("orders/{id}")
    fun refuseOrder(
        @Path("id") id: String?,
        @Body body: RefuseOrderWrapper
    ): Call<ApiResponse<OrderResponse>>

    @GET("orders")
    fun getAllOrder(
//        @Query("filters[createdAt][\$contains]") after: String,
        @Query("filters[booth]") booth: String?,
        @Query("populate") populate: String?,
        @Query("sort[0]") sort: String?
    ): Call<ApiResponse<List<OrderResponse>>>

}

interface OrderDetailsService {
    @POST("order-details")
    fun addOrderDetails(@Body orderData: OrderDetailsDataWrapper): Call<ApiResponse<OrderDetailsResponse>>

    @GET("order-details")
    fun getAllOrderDetails(
        @Query("filters[orderID]") orderID: String?,
        @Query("populate") populate: String?
    ): Call<ApiResponse<List<OrderDetailsResponse>>>

    @GET("foods")
    fun getFoodImg(@Query("filters[id]") id: String?, @Query("populate") populate: String?): Call<ApiResponse<List<FoodResponse>>>
}