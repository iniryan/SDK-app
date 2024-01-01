package com.example.projectuasmobile.frontend.customer

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.BottomNavCustomer
import com.example.projectuasmobile.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerTransaction(navController: NavController, context: Context = LocalContext.current) {
    val primaryColorOrg = Color(0xFFFF5F00)
    Scaffold(
        bottomBar = {
            BottomNavCustomer(navController = navController)
        },

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 36.dp)
                    .background(color = Color(0xFFFFFFFF))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Detail Transaksi",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            color = Color(0xFFFF5F00),
                        )
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 48.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.padding(end = 48.dp)) {
                            Text(
                                text = "Nama Pemesan", style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                            Text(
                                text = "Mario", style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFFF5F00),
                                )
                            )
                        }
                        Column {
                            Text(
                                text = "Nomor Meja", style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                            Text(
                                text = "No. 11", style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFFF5F00),
                                )
                            )
                        }

                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.padding(end = 48.dp)) {
                            Text(
                                text = "Total Harga", style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                            Text(
                                text = "Rp50.000", style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFFF5F00),
                                )
                            )
                        }
                        Column {
                            Text(
                                text = "Status Pesanan", style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                            Text(
                                text = "Pending", style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFFF5F00),
                                )
                            )
                        }

                    }

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    Text(
                        text = "Catatan", style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    Text(
                        text = "ini catatan pesanan", style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    Text(
                        text = "Detail Pesanan", style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    //list makanan dan harga lalu total, dan status pesanan
                }
            }
        }
    }
}



