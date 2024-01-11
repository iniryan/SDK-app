package com.example.projectuasmobile.frontend.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
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
fun RolePick(navController: NavController) {
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
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0xCCFF5F00),
                    textAlign = TextAlign.Center,

                    )
            )
            Text(
                text = "DIGITAL",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(0xFFFF5F00),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "KULINER",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0xCCFF5F00),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Text(
                text = "Sebagai Apa Kamu ?",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            IconButton(
                onClick = { navController.navigate("homepage") },
                modifier = Modifier
                    .semantics { text = AnnotatedString("pembeli") }
                    .testTag("pembeli_tag")
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(1.dp, Color(0xFFFF5F00), RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pembeli),
                    contentDescription = "image role pembeli",
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            IconButton(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .semantics { text = AnnotatedString("penjual") }
                    .testTag("penjual_tag")
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(1.dp, Color(0xFFFF5F00), RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.penjual),
                    contentDescription = "image role penjual",
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 24.dp))
            Text(
                text = "Ayo pilih role kamu sebelum menggunakan aplikasi. Pilih Pembeli jika kamu ingin memesan makanan. Pilih Penjual jika kamu adalah pemilik booth.",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}