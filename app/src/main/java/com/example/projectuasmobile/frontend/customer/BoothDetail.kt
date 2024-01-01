package com.example.projectuasmobile.frontend.customer

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.OrderDetailsData
import com.example.projectuasmobile.data.OrderDetailsDataWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.service.FoodService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun BoothDetail(
    navController: NavController,
    boothID: String?,
    boothName: String?,
    boothDescription: String?,
    context: Context = LocalContext.current
) {
    val title = remember { mutableStateOf(boothName ?: "") }
    val desc = remember { mutableStateOf(boothDescription ?: "") }
    val boothID = remember { mutableStateOf(boothID ?: "") }

    val listMenu = remember { mutableStateListOf<FoodResponse>() }
    val baseUrl = "http://10.0.2.2:1337/api/"
    val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(FoodService::class.java)
    val call = retrofit.getAllFood(boothID.value, "*")
    call.enqueue(object : Callback<ApiResponse<List<FoodResponse>>> {
        override fun onResponse(
            call: Call<ApiResponse<List<FoodResponse>>>,
            response: Response<ApiResponse<List<FoodResponse>>>
        ) {
            if (response.isSuccessful) {
                listMenu.clear()
                response.body()?.data!!.forEach { menuResponse ->
                    listMenu.add(menuResponse)
                }
            } else {
                Toast.makeText(
                    context,
                    "Error: ${response.code()} - ${response.message()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<ApiResponse<List<FoodResponse>>>, t: Throwable) {
            print(t.message)
        }

    })
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .background(color = Color(0x1F000000)),
                painter = painterResource(id = R.drawable.thumbnail),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(9.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = title.value, style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 36.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                color = Color(0xFF333333),
                            )
                        )
                        Text(
                            text = desc.value,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Color(0xFF9F9F9F),
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(0xFFFFFFFF))
                            .padding(bottom = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Divider(
                            modifier = Modifier
                                .border(width = 1.dp, color = Color(0xFFECECEC))
                                .padding(1.dp)
                                .height(2.dp)
                                .background(color = Color(0xFFEEEEEE))
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(390.dp)
                                    .height(62.dp)
                                    .padding(
                                        start = 16.dp, top = 18.dp, end = 10.dp, bottom = 18.dp
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(
                                    10.dp, Alignment.Start
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "Daftar Menu", style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 26.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                        color = Color(0xFF333333),
                                    )
                                )
                            }
                            val orderItems =
                                remember { mutableStateListOf<OrderDetailsDataWrapper>() }
                            LazyColumn {
                                listMenu.forEach { menuResponse ->
                                    item {
                                        Divider(
                                            modifier = Modifier
                                                .width(390.dp)
                                                .height(1.dp)
                                                .background(color = Color(0xFFEEEEEE))
                                        )
                                        Column(
                                            modifier = Modifier
                                                .width(390.dp)
                                                .height(157.5.dp)
                                                .background(color = Color(0xFFFFFFFF))
                                                .padding(start = 16.dp, top = 14.dp, end = 16.dp),
                                            verticalArrangement = Arrangement.spacedBy(
                                                16.dp, Alignment.CenterVertically
                                            ),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .width(358.dp)
                                                    .height(127.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.Top,
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .width(218.dp)
                                                        .height(127.dp),
                                                    verticalArrangement = Arrangement.spacedBy(
                                                        4.dp, Alignment.Top
                                                    ),
                                                    horizontalAlignment = Alignment.Start,
                                                ) {
                                                    Text(
                                                        text = menuResponse.attributes.foodName,

                                                        style = TextStyle(
                                                            fontSize = 15.sp,
                                                            lineHeight = 19.sp,
                                                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                            color = Color(0xFF333333),
                                                        )
                                                    )
                                                    Text(
                                                        text = menuResponse.attributes.foodDescription,

                                                        style = TextStyle(
                                                            fontSize = 13.sp,
                                                            lineHeight = 18.sp,
                                                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                            color = Color(0xFF757575),
                                                        )
                                                    )
                                                    Text(
                                                        text = "Rp" + menuResponse.attributes.foodPrice.toString(),
                                                        style = TextStyle(
                                                            fontSize = 12.sp,
                                                            lineHeight = 20.sp,
                                                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                            color = Color(0xFF333333),
                                                        )
                                                    )
                                                    Row(
                                                        modifier = Modifier
                                                            .padding(top = 8.dp)
                                                            .width(218.dp)
                                                            .height(28.dp),
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            16.dp, Alignment.Start
                                                        ),
                                                        verticalAlignment = Alignment.CenterVertically,
                                                    ) {

                                                        Row(
                                                            horizontalArrangement = Arrangement.spacedBy(
                                                                12.dp, Alignment.CenterHorizontally
                                                            ),
                                                            verticalAlignment = Alignment.CenterVertically,
                                                        ) {
                                                            var quantity by remember {
                                                                mutableIntStateOf(
                                                                    0
                                                                )
                                                            }
                                                            var orderItem by remember {
                                                                mutableStateOf<OrderDetailsDataWrapper?>(
                                                                    null
                                                                )
                                                            }
                                                            val existingItemIndex =
                                                                orderItems.indexOf(orderItem)

                                                            if (quantity != 0) {
                                                                IconButton(onClick = {
                                                                    if (quantity > 0) {
                                                                        quantity--
                                                                        if (quantity == 0 && orderItem != null) {
                                                                            orderItems.remove(
                                                                                orderItem!!
                                                                            )
                                                                            orderItem = null
                                                                        }
                                                                    }
                                                                }) {
                                                                    Icon(
                                                                        painter = painterResource(id = R.drawable.minus_icon),
                                                                        contentDescription = "Minus"
                                                                    )
                                                                }
                                                            }
                                                            Text(
                                                                text = "$quantity",

                                                                style = TextStyle(
                                                                    fontSize = 14.sp,
                                                                    lineHeight = 14.sp,
                                                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                                    color = Color(0xFF333333),
                                                                )
                                                            )
                                                            IconButton(onClick = {
                                                                quantity++
                                                                if (orderItem == null) {
                                                                    val newOrderItem =
                                                                        OrderDetailsDataWrapper(
                                                                            OrderDetailsData(
                                                                                orderID = "1",
                                                                                foods = menuResponse,
                                                                                qty = quantity
                                                                            )
                                                                        )
                                                                    if (!orderItems.contains(
                                                                            newOrderItem
                                                                        )
                                                                    ) {
                                                                        orderItems.add(newOrderItem)
                                                                        orderItem = newOrderItem
                                                                    }
                                                                } else {

                                                                    if (existingItemIndex != null) {
                                                                        orderItems[existingItemIndex] =
                                                                            orderItem!!.copy(
                                                                                orderDetailsData = orderItem!!.orderDetailsData.copy(
                                                                                    qty = quantity
                                                                                )
                                                                            )
                                                                        orderItem =
                                                                            orderItems[existingItemIndex]
                                                                    }
                                                                }

                                                            }) {
                                                                Icon(
                                                                    painter = painterResource(id = R.drawable.plus_icon),
                                                                    contentDescription = "Add"
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                                val imgurl =
                                                    menuResponse.attributes.foodImg?.data?.attributes!!.url
                                                Image(
                                                    modifier = Modifier
                                                        .width(100.dp)
                                                        .height(100.dp)
                                                        .clip(RoundedCornerShape(8.dp)),
                                                    contentScale = ContentScale.Crop,
                                                    painter = rememberAsyncImagePainter("http://10.0.2.2:1337" + imgurl),
                                                    contentDescription = "image description"
                                                )

                                            }
                                            Divider(
                                                modifier = Modifier
                                                    .width(358.dp)
                                                    .height(0.5.dp)
                                                    .background(color = Color(0xFFEEEEEE))
                                            )
                                        }
                                    }
                                }
                            }
                            ElevatedButton(
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp, horizontal = 16.dp)
                                    .height(48.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                ),
                                shape = RoundedCornerShape(8.dp),
                                onClick = {
                                    val objectArrayParameterString = Gson().toJson(orderItems)
                                    val listReplace = objectArrayParameterString.replace(
                                        "/uploads/",
                                        "::uploads::"
                                    )
                                    navController.navigate("checkout/" + listReplace)
                                }
                            )

                            {
                                Text(
                                    text = "Tambah Pesanan",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}