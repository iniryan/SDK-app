package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.projectuasmobile.BottomNavigation
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuList(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
//    val listMenu = remember { mutableStateListOf<FoodResponse>() }
//    val baseUrl = "http://10.0.2.2:1337/api/"
//    val retrofit =
//        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
//            .build().create(FoodService::class.java)
//    val call = retrofit.getData()
//    call.enqueue(object : Callback<List<FoodResponse>> {
//        override fun onResponse(
//            call: Call<List<FoodResponse>>, response: Response<List<FoodResponse>>
//        ) {
//            if (response.code() == 200) {
//                listMenu.clear()
//                response.body()?.forEach { menuResponse ->
//                    listMenu.add(menuResponse)
//                }
//            } else if (response.code() == 400) {
//                print("error login")
//                Toast.makeText(
//                    context, "Username atau password salah", Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//        override fun onFailure(call: Call<List<FoodResponse>>, t: Throwable) {
//            print(t.message)
//        }
//
//    })
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
                    .padding(horizontal = 24.dp, vertical = 14.dp)
                    .verticalScroll(rememberScrollState()),
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
//            nanti bakal loop row dengan data dari api
//                LazyColumn {
//                    listMenu.forEach { menu ->
//                        item {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    12.dp,
                                    Alignment.Start
                                ),
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 14.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.dummy),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(84.dp)
                                )
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
//                                        text = menu.foodName, style = TextStyle(
                                        text = "Lalapan Ayam", style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 17.64.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                            color = Color(0xFF1E1E1E),
                                        )
                                    )
                                    Text(
                                        text = "Rp10.000", style = TextStyle(
//                                        text = menu.foodPrice.toString(), style = TextStyle(
                                            fontSize = 12.sp,
                                            lineHeight = 17.64.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                            color = Color(0x801E1E1E),
                                        )
                                    )
                                    Spacer(modifier = Modifier.padding(top = 2.dp))
                                    Divider(
                                        modifier = Modifier
                                            .border(width = 1.dp, color = Color(0xFFE0E0E0))
                                            .fillMaxSize()
                                            .height(0.2.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(top = 2.dp))
                                    Text(
                                        text = "Nasi + Ayam + Lalapan + Sambal",
//                                        text = menu.foodDescription,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            lineHeight = 17.64.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                            color = Color(0x801E1E1E),
                                        )
                                    )
                                }
                            }
//                        }
//                    }
//
//                }

            }
        }
    }
}