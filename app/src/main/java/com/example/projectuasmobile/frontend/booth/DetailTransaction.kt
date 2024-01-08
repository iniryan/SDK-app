package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailTransaction(navController: NavController, context: Context = LocalContext.current) {
    val primaryColorOrg = Color(0xFFFF5F00)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(color = Color(0xFFFFFFFF))
        ) {
            IconButton(modifier = Modifier
                .padding(bottom = 16.dp)
                .background(
                    color = Color(0xFFFF5F00), shape = RoundedCornerShape(100.dp)
                ), onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    modifier = Modifier.size(25.dp),
                    tint = Color.White
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                )
                Column {
                    Text(
                        text = "Thank you!",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF212529),
                        )
                    )
                    Text(
                        text = "Your order #BE12345 has been placed.",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF212529),

                            )
                    )
                }
            }
            Text(
                text = "Tanggal Transaksi: 17/02/2020 12:45",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
            )
            Text(
                text = "Detail Transaksi",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Nama Customer: Banu Elson",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF212529),
                    )
                )
                Text(
                    text = "Telepon: +49 179 111 1010",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF212529),
                    )
                )
                Text(
                    text = "Table number: 12",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF212529),
                    )
                )
                Text(
                    text = "Catatan: Leibnizstraße 16, Wohnheim 6, No: 8X \nClausthal-Zellerfeld, Germany",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF212529),
                    )
                )
            }
            Text(
                text = "Order Item",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(vertical = 12.dp)
            )
//                Lazycolumn
            Text(
                text = "Order Summary",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = "Total",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF212529),
                        )
                    )
                    Text(
                        text = "$147,45",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF212529),
                            textAlign = TextAlign.Right,
                            letterSpacing = 0.3.sp,
                        )
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ElevatedButton(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .height(64.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFFFF5F00)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { }
                )
                {
                    Text(
                        text = "Terima Pesanan",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                ElevatedButton(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .height(64.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { }
                )
                {
                    Text(
                        text = "Tolak Pesanan",
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



