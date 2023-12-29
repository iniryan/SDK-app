package com.example.projectuasmobile.data

import com.google.gson.annotations.SerializedName

data class OrderDataWrapper(@SerializedName("data") val orderData: OrderData)
data class OrderData(
    @SerializedName("customerName")
    val customerName: String,
    @SerializedName("tableNumber")
    val tableNumber: String,
//    @SerializedName("order_details")
//    val order_details:
)
