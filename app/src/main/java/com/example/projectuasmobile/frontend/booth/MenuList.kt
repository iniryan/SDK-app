package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.projectuasmobile.BottomNavigation
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.service.FoodService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuList(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val listMenu = remember { mutableStateListOf<FoodResponse>() }
    val boothId = preferencesManager.getData("boothID")

    //LOKAL STRAPI
    //val baseUrl = "http://10.0.2.2:1337/api/"
    val baseUrl = "https://api2.tnadam.me/api/"
    val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(FoodService::class.java)
    val call = retrofit.getAllFood(boothId, "*")
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
                    context, "Error: ${response.code()} - ${response.message()}", Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<ApiResponse<List<FoodResponse>>>, t: Throwable) {
            print(t.message)
        }

    })
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("addmenu")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        },

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(top = 14.dp, bottom = 14.dp))
                Text(
                    text = "Daftar Menu", style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        textAlign = TextAlign.Left,
                        color = Color(0xFFFF5F00),
                    ), modifier = Modifier.align(Alignment.Start)
                )
                LazyColumn {
                    if(listMenu.isNotEmpty()) {
                        listMenu.forEach { menu ->
                            item {
                                val currentValue = menu.attributes.foodImg?.data?.attributes!!.url
                                val newUrl = currentValue.replace("/uploads/", "::uploads::")
                                Row(horizontalArrangement = Arrangement.spacedBy(
                                    12.dp, Alignment.Start
                                ),
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 14.dp)
                                        .background(
                                            color = Color(0xFFF4F8FB),
                                            shape = RoundedCornerShape(size = 10.dp)
                                        )
                                        .clickable { navController.navigate("editMenu/" + menu.id + "/" + menu.attributes.foodName + "/" + menu.attributes.foodDescription + "/" + menu.attributes.foodPrice + "/" + newUrl) }) {
                                    val imgurl = menu.attributes.foodImg?.data?.attributes!!.url
                                    Image(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(100.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop,
//                                    painter = rememberAsyncImagePainter("http://10.0.2.2:1337" + imgurl),
                                        painter = rememberAsyncImagePainter("https://api2.tnadam.me$imgurl"),
                                        contentDescription = "image description"
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            1.dp,
                                            Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.Start,
                                        modifier = Modifier.fillMaxWidth().padding(14.dp)
                                    ) {
                                        Text(
                                            text = menu.attributes.foodName, style = TextStyle(
                                                fontSize = 18.sp,
                                                lineHeight = 17.64.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                                color = Color(0xFF0B1527),
                                            )
                                        )
                                        Text(
                                            text = "Rp" + menu.attributes.foodPrice.toString(),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                lineHeight = 17.64.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF0B1527)
                                            )
                                        )
                                        Spacer(modifier = Modifier.padding(top = 2.dp))
                                        Divider(
                                            modifier = Modifier
                                                .border(
                                                    width = 1.dp, color = Color(0xFFE0E0E0)
                                                )
                                                .fillMaxSize()
                                                .height(1.dp)
                                        )
                                        Spacer(modifier = Modifier.padding(top = 2.dp))
                                        Text(
                                            text = menu.attributes.foodDescription,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis, style = TextStyle(
                                                fontSize = 14.sp,
                                                lineHeight = 17.64.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                color = Color(0x801E1E1E),
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.padding(vertical = 60.dp))
                        }
                    } else {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Column(
                                    modifier = Modifier.padding(50.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        text = "Tidak ada menu tersedia",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                            color = Color(0xFF495057),
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
}