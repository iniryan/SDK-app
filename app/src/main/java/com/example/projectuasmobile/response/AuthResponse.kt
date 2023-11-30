package com.example.projectuasmobile.response

import com.google.gson.annotations.SerializedName

class AuthResponse {
    @SerializedName("jwt")
    var jwt: String = ""
}