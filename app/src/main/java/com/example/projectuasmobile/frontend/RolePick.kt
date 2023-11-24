package com.example.projectuasmobile.frontend

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectuasmobile.R

@Composable
fun RolePick(){
    Box(){
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),verticalArrangement = Arrangement.spacedBy(-14.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,)
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
        Column {
            Image(
                painter = painterResource(id = R.drawable.pembeli),
                contentDescription = "image description",
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(id = R.drawable.penjual),
                contentDescription = "image description",
                contentScale = ContentScale.Fit
            )
        }
    }
}