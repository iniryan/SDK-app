package com.example.projectuasmobile.frontend.auth

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.projectuasmobile.R

@Composable
fun RolePick(navController: NavController, context: Context = LocalContext.current) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy((-14).dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Text(
                text = "SENTRA",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xCCFF5F00),
                    textAlign = TextAlign.Center,

                    )
            )

            Text(
                text = "DIGITAL",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF5F00),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "KULINER",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xCCFF5F00),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Text(
                text = "Sebagai Apa Kamu ?",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 132.dp))
            Image(
                painter = painterResource(id = R.drawable.pembeli),
                contentDescription = "image description",
                contentScale = ContentScale.Fit,
                modifier = Modifier.clickable { navController.navigate("homepage") }
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Image(
                painter = painterResource(id = R.drawable.penjual),
                contentDescription = "image description",
                contentScale = ContentScale.Fit,
                modifier = Modifier.clickable { navController.navigate("login") }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 220.dp, top = 220.dp, end = 0.dp, bottom = 0.dp),
        ) {
            Text(
                text = "PEMBELI ",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF5F00),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 140.dp, top = 250.dp, end = 0.dp, bottom = 0.dp),
        ) {
            Text(
                text = "Lakukan Pemesanan Melalui " +
                        "Handphonemu",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xE51E1E1E),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 76.dp, top = 470.dp, end = 0.dp, bottom = 0.dp),
        ) {
            Text(
                text = "PENJUAL ",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF5F00),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, top = 500.dp, end = 160.dp, bottom = 0.dp),
        ) {
            Text(
                text = "Terima Pesanan dan Manage Menu dengan Praktis ",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xE51E1E1E),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ayo pilih role kamu sebelum menggunakan aplikasi. Pilih Pembeli jika kamu ingin memesan makanan. Pilih Penjual jika kamu adalah pemilik booth.",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                )
            )

        }
    }
}