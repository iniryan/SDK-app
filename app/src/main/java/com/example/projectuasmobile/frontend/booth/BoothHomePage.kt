package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoothHomePage(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

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
                        .border(width = 1.dp, color = Color(0xFFFF5F00))
                        .fillMaxSize()
                        .height(3.dp)
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))

                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier.size(width = 440.dp, height = 120.dp)
                                .clickable { navController.navigate("detailpesanan") },
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
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
                                            color = Color(0xFFFF5F00)
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                    }
                }


//            nanti bakal loop row dengan data dari api

            }

        }


