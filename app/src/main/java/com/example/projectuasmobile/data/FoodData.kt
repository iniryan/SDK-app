package com.example.projectuasmobile.data

import com.google.gson.annotations.SerializedName

data class FoodDataWrapper(@SerializedName("data") val foodData: FoodData)

data class FoodData(
    val foodName: String,
    val foodDescription: String,
    val foodPrice: Int
)
