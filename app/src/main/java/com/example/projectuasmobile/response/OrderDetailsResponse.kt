package com.example.projectuasmobile.response

class OrderDetailsResponse {
    var id: Int = 0
    var attributes: OrderDetailsAttributes = OrderDetailsAttributes()
}

class OrderDetailsAttributes {
    val orderID: ApiResponse<OrderResponse>? = null
    val foods: ApiResponse<List<FoodResponse>>? = null
    val qty: Int = 0
}