package com.example.projectuasmobile.data

import com.google.gson.annotations.SerializedName

data class OrderDetailsDataWrapper(@SerializedName("data") val orderDetailsData: OrderDetailsData)
data class OrderDetailsData(
    @SerializedName("orderID")
    val orderID: String,
    @SerializedName("foods")
    val foods: String,
    @SerializedName("qty")
    var qty: Int
)
