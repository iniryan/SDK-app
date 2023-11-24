package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("jwt")
    var jwt: String = ""
}