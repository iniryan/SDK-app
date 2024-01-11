package com.example.projectuasmobile.frontend.customer

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.R

@Composable
fun PaymentSuccess(navController: NavController, context: Context = LocalContext.current){

    val primaryColorOrg = Color(0xFFFF5F00)
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Terimakasih Telah Memesan",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xCCFF5F00),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Mohon Bersabar, Pesanan Anda Sedang Diproses",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                )
            )
            Button(onClick = {navController.navigate("homepage")}, modifier = Modifier
                .width(217.dp)
                .height(64.dp)
                .padding(start = 10.dp, top = 12.dp, end = 10.dp, bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColorOrg)
            ) {
                Text(

                    text = "Kembali Ke Beranda",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(600),
                        color = Color.White,
                    )
                )
            }
            }

        }
    }
