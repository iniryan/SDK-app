package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

class OrderResponse {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: OrderAttributes = OrderAttributes()
}
class OrderAttributes {
    @SerializedName("customerName")
    val customerName: String = ""
    @SerializedName("tableNumber")
    val tableNumber: String = ""
    @SerializedName("customerNumber")
    val customerNumber: String = ""
    @SerializedName("notes")
    val notes: String = ""
    @SerializedName("total")
    val total: Int = 0
    @SerializedName("status")
    val status: String = ""
    @SerializedName("booth")
    val booth: ApiResponse<BoothResponse>? = null
    @SerializedName("createdAt")
    val createdAt: String = ""
    @SerializedName("alasanTolak")
    val alasanTolak: String = ""
    @SerializedName("orderDetails")
    val orderDetails: List<ApiResponse<OrderDetailsResponse>>? = null
}