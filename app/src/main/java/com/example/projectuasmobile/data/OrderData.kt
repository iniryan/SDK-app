package com.example.projectuasmobile.data

import com.example.projectuasmobile.response.FoodResponse
import com.google.gson.annotations.SerializedName

data class OrderDataWrapper(@SerializedName("data") val orderData: OrderData)
data class OrderData(
    @SerializedName("customerName")
    val customerName: String,
    @SerializedName("tableNumber")
    val tableNumber: String,
    @SerializedName("customerNumber")
    val customerNumber: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("booth")
    val booth: Int
//    @SerializedName("order_details")
//    val order_details:
)
data class OrderDetailsDataWrapper(@SerializedName("data") val orderDetailsData: OrderDetailsData)
data class OrderDetailsData(
    @SerializedName("orderID")
    var orderID: String,
    @SerializedName("foods")
    var foods: FoodResponse,
    @SerializedName("qty")
    var qty: Int
)

data class UpdateStatusWrapper(@SerializedName("data") val orderData: UpdateStatusOrder)
data class UpdateStatusOrder(
    @SerializedName("status")
    val status: String
)

data class RefuseOrderWrapper(@SerializedName("data") val orderData: RefuseOrder)

data class RefuseOrder(
    @SerializedName("status")
    val status: String,
    @SerializedName("alasanTolak")
    val alasanTolak: String
)