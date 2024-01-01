package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

class BoothResponse {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: BoothAttributes = BoothAttributes()
}

class BoothAttributes{
    @SerializedName("boothName")
    val boothName: String = ""
    @SerializedName("boothDescription")
    val boothDescription: String = ""
    @SerializedName("open")
    val open: Boolean = true
    @SerializedName("owner")
    val owner: ApiResponse<User>? = null
    @SerializedName("foods")
    val foods: ApiResponse<List<FoodResponse>>? = null
    @SerializedName("boothImg")
    var boothImg: ApiResponse<BoothImgResponse>? = null
}

class BoothImgResponse {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("attributes")
    var attributes: BoothImgAttributes = BoothImgAttributes()
}

class BoothImgAttributes {
    @SerializedName("name")
    var name: String = ""
    @SerializedName("url")
    var url : String = ""
}
