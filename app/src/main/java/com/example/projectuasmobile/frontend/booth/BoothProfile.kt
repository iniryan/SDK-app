package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoothProfile (navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

    Scaffold(
        floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate("EditBooth")
        }) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
    },
        bottomBar = {
            BottomNavigation(navController = navController)
        },

        ) {
        Box (modifier = Modifier.fillMaxSize()
            ){

            Image(
                modifier = Modifier
                    .width(430.dp)
                    .height(270.dp)
                    .alpha(0.5f),
                painter = painterResource(id = R.drawable.thumbnail),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
            )

        }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 42.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {


                Image(
                    modifier = Modifier
                        .width(114.dp)
                        .height(114.dp),
                    painter = painterResource(id = R.drawable.profilepict),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = "Halimah",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )
            }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp, start = 12.dp, end = 12.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,

            ) {
            Text(
                text = "Profile",
                style = TextStyle(
                    fontSize =28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF5F00),
                )
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "Nama Booth",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFF5F00),
                )
            )
            Text(
                text = "Warung Nikmat Bu Halima",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFF5F00),
                )
            )
            Spacer(modifier = Modifier.padding(top =20.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.padding(top =20.dp))

            Text(
                text = "Deskripsi Booth",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFF5F00),
                )
            )
            Text(
                text = "Menjual berbagai penyetan ayam, ikan, dan nasi campur",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFF5F00),
                )
            )


        }
    Button(onClick = {
        preferencesManager.clearData()
        navController.navigate("login") },
        modifier = Modifier.padding(start = 300.dp, top = 12.dp)) {
        Text(text = "Logout")
    }
    }
}
