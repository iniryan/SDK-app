package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

class Booth {
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
    @SerializedName("owner")
    val owner: Int = 0
    @SerializedName("open")
    val open: Boolean = true
}
data class BoothResponse<T>(
    @SerializedName("data")
    val data: T?
)