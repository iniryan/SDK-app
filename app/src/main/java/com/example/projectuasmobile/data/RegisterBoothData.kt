package com.example.projectuasmobile.data

import com.google.gson.annotations.SerializedName

data class BoothDataWrapper(@SerializedName("data") val boothData: RegisterBoothData)
data class RegisterBoothData(val boothName: String, val boothDescription: String, val owner: Int)

