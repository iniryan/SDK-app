package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("attributes")
    var attributes: UserAttributes = UserAttributes()
}
class UserAttributes {
    @SerializedName("username")
    val username: String = ""
    @SerializedName("email")
    val email: String = ""
    @SerializedName("provider")
    val provider: String = ""
    @SerializedName("confirmed")
    val confirmed: Boolean = false
    @SerializedName("blocked")
    val blocked: Boolean = false
    @SerializedName("createdAt")
    val createdAt: String = ""
    @SerializedName("updatedAt")
    val updatedAt: String = ""
    @SerializedName("fullname")
    val fullname: String = ""
}

class UserResponse {
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("username")
    val username: String = ""
    @SerializedName("email")
    val email: String = ""
    @SerializedName("provider")
    val provider: String = ""
    @SerializedName("confirmed")
    val confirmed: Boolean = false
    @SerializedName("blocked")
    val blocked: Boolean = false
    @SerializedName("createdAt")
    val createdAt: String = ""
    @SerializedName("updatedAt")
    val updatedAt: String = ""
    @SerializedName("fullname")
    val fullname: String = ""
    @SerializedName("booth")
    val booth: BoothResponse? = null
}


