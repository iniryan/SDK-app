package com.example.projectuasmobile.frontend.customer

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R

@Composable
fun Receipt(navController: NavController?, total: String?, customerName: String?, customerNumber: String?, transactionTime: String?, boothName: String?, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val custName = remember { mutableStateOf(customerName ?: "") }
    val custNumber = remember { mutableStateOf(customerNumber ?: "") }
    val totalStruk = remember { mutableStateOf(total ?: "") }
    val transTime = remember { mutableStateOf(transactionTime ?: "") }
    val booth = remember { mutableStateOf(boothName ?: "") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.checklist),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Payment Success!",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Color(0xFF121212),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = "Your payment has been successfully done.",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color(0xFF474747),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, top = 20.dp, end = 40.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Total Pembayaran",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4B4E52),
                            )
                        )
                        Text(
                            text = "Rp"+totalStruk.value,
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF121212),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Status Order",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4B4E52),
                            )
                        )
                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color(0x1F23A26D),
                                    shape = RoundedCornerShape(size = 23.dp)
                                )
                                .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.Top,
                        ) {
                            Text(
                                text = "Selesai",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    lineHeight = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF23A26D),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                    }
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), color = Color(0xFFE0E0E0))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Nomor Telepon",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4B4E52),
                            )
                        )
                        Text(
                            text = custNumber.value,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF121212),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Nama Pemesan",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4B4E52),
                            )
                        )
                        Text(
                            text = custName.value,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF121212),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Nama Booth/Kios",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4B4E52),
                            )
                        )
                        Text(
                            text = booth.value,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF121212),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Tanggal Transaksi",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4B4E52),
                            )
                        )
                        Text(
                            text = transTime.value,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF121212),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 40.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
//            ElevatedButton(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 2.dp, horizontal = 16.dp)
//                    .height(64.dp)
//                    .border(width = 1.dp, color = Color(0xFFFF5F00), shape = RoundedCornerShape(size = 8.dp)),
//                colors = ButtonDefaults.buttonColors(
//                    contentColor = Color(0xFFFF5F00),
//                    containerColor = Color(0xFFFFFFFF)
//                ),
//                shape = RoundedCornerShape(8.dp),
//                onClick = {
//                }
//            )
//            {
//                Text(
//                    text = "Download Invoice",
//                    style = TextStyle(
//                        fontSize = 16.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
//                        color = Color(0xFFFF5F00),
//                        textAlign = TextAlign.Center,
//                    )
//                )
//            }
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 16.dp)
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFFFF5F00)
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    preferencesManager.clearData()
                    navController!!.navigate("success")
                }
            )
            {
                Text(
                    text = "Selesai",
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

@Composable
fun SuccessPage(navController: NavController, context: Context = LocalContext.current) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF)),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.checklist),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                )
                Column(
                    modifier = Modifier.padding(top = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Pesanan Selesai!",
                        style = TextStyle(
                            fontSize = 28.sp,
                            lineHeight = 28.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Color(0xFF121212),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = "Pesananmu telah selesai, terima kasih sudah membeli menggunakan SDK-app",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color(0xFF474747),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 40.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 16.dp)
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFFFF5F00)
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    navController.navigate("homepage")
                }
            )
            {
                Text(
                    text = "Selanjutnya",
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