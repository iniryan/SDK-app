package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("data")
    val data: T? = null
)

class FoodResponse {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("foodName")
    var foodName: String = ""
    @SerializedName("foodDescription")
    var foodDescription: String = ""
    @SerializedName("foodPrice")
    var foodPrice: Int = 0
    @SerializedName("createdAt")
    var createdAt: String = ""
    @SerializedName("updatedAt")
    var updatedAt: String = ""
}