package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.projectuasmobile.response.UserResponse
import com.example.projectuasmobile.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoothHomePage(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val userID = preferencesManager.getData("userID")

    val baseUrl = "http://10.0.2.2:1337/api/"
    val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(UserService::class.java)
    val call = retrofit.getDataById(userID.toInt(), "booth")
    call.enqueue(object : Callback<UserResponse> {
        override fun onResponse(
            call: Call<UserResponse>, response: Response<UserResponse>
        ) {
            if (response.code() == 200) {
                val booth = response.body()?.booth
                if (booth != null) {
                    preferencesManager.saveData("boothID", booth.id.toString())
                }
            } else if (response.code() == 400) {
                print("error login")
                Toast.makeText(
                    context, "Username atau password salah", Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            print(t.message)
        }

    })

    val primaryColorOrg = Color(0xFFFF5F00)
    Scaffold(
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
                    text = "Pesanan Masuk", style = TextStyle(
                        fontSize = 28.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        textAlign = TextAlign.Left,
                        color = Color(0xFFFF5F00),
                    ), modifier = Modifier.align(Alignment.Start)
                )
                Divider(
                    modifier = Modifier
                        .border(width = 1.dp, color = primaryColorOrg)
                        .fillMaxSize()
                        .height(3.dp)
                )
                Spacer(modifier = Modifier.padding(top = 18.dp))
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .width(440.dp)
                        .clickable { navController.navigate("detailpesanan") },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Pesanan Dari : ",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "Nomor Meja :",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "Cek Selengkapnya",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    textAlign = TextAlign.End,
                                    color = primaryColorOrg
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))
            }
        }
    }
}


