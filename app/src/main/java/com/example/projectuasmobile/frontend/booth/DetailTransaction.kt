package com.example.projectuasmobile.frontend.booth

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.BottomNavigation
import com.example.projectuasmobile.R

@Composable
fun DetailTransaction(navController: NavController, context: Context = LocalContext.current) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .background(
                            color = Color(0xFFFF5F00),
                            shape = RoundedCornerShape(100.dp)
                        ),
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        modifier = Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
                Text(
                    text = "Detail Transaksi",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFF5F00),
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp)
                .background(color = Color(0xFFFFFFFF))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(color = Color(0x1F000000)),
                painter = painterResource(id = R.drawable.thumbnail),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "Nama Pemesan", style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFF5F00),
                    )
                )
                Text(
                    text = "Mario", style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFF5F00),
                    )
                )

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Text(
                    text = "Detail Pesanan", style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFF5F00),
                    )
                )
                Text(
                    text = "ini detail pesanan", style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFF5F00),
                    )
                )
            }
        }
    }
}