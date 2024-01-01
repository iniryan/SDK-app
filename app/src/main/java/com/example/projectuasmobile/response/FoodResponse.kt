package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

class FoodResponse {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: FoodAttributes = FoodAttributes()
}

class FoodAttributes {
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
    @SerializedName("foodImg")
    var foodImg: ApiResponse<FoodImgResponse>? = null
}

class FoodImgResponse {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("attributes")
    var attributes: FoodImgAttributes = FoodImgAttributes()
}

class FoodImgAttributes {
    @SerializedName("name")
    var name: String = ""
    @SerializedName("url")
    var url : String = ""
}