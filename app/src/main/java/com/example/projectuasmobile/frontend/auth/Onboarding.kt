package com.example.projectuasmobile.frontend.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.R

@Composable
fun OnboardingScreen(navController: NavController) {
    val primaryColorOrg = Color(0xFFFF5F00)
    Box(
        modifier = Modifier
            .width(430.dp)
            .height(932.dp)
            .background(color = primaryColorOrg)
    ) {
        Row (modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)) {
            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(32.dp),
                painter = painterResource(id = R.drawable.logokecil),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Text(
                modifier = Modifier
                    .width(104.dp)
                    .height(30.dp),
                text = "SDK-App",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFEEEEEE),
                )
            )

        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.splashtext),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
        }
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally){
            Button(
                onClick = {
                    navController.navigate("rolepick")
                }, Modifier
                    .width(327.dp)
                    .height(72.dp)
                    .padding(start = 10.dp, top = 12.dp, end = 10.dp, bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Mulai Gunakan Aplikasi",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontWeight = FontWeight(600),
                        color = primaryColorOrg,
                    )
                )

            }
        }

    }
}