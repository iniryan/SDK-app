package com.example.projectuasmobile.frontend

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.BottomNavigation
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val searchField = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        bottomBar = {
            BottomNavigation { selectedItem ->
                when (selectedItem) {
                    "Beranda" -> {
                        // Handle Beranda click
                    }

                    "Kios" -> {
                        // Handle Kios click
                    }

                    "Transaksi" -> {
                        // Handle Transaksi click
                    }

                    "Keluar" -> {
                        preferencesManager.clearData()
                        navController.navigate("login")
                    }
                }
            }
        },

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(value = searchField.value,
                    onValueChange = {
                        searchField.value = it
                    },
                    singleLine = true,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(2.dp)
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFFFF5F00),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "search",
                        )
                    },
                    placeholder = { Text(text = "Pencarian") })
                Spacer(modifier = Modifier.padding(top = 14.dp, bottom = 14.dp))
                Text(
                    text = "Daftar Kios", style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        textAlign = TextAlign.Left,
                        color = Color(0xFFFF5F00),
                    ), modifier = Modifier.align(Alignment.Start)
                )
//            nanti bakal loop row dengan data dari api
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dummy),
                        contentDescription = "image description",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(80.dp)
                            .height(84.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Warkop Markaz", style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 17.64.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Color(0xFF1E1E1E),
                            )
                        )
                        Text(
                            text = "Booth No.1", style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 17.64.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Color(0x801E1E1E),
                            )
                        )
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        Divider(modifier = Modifier
                            .border(width = 1.dp, color = Color(0xFFE0E0E0))
                            .fillMaxSize()
                            .height(0.2.dp))
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        Text(
                            text = "Menyediakan berbagai minuman sachet, kopi, dan Rokok Batangan ",
                            style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 17.64.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Color(0x801E1E1E),
                            )
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dummy),
                        contentDescription = "image description",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(80.dp)
                            .height(84.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Warung Nikmat bu Halima", style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 17.64.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Color(0xFF1E1E1E),
                            )
                        )
                        Text(
                            text = "Booth No.2", style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 17.64.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Color(0x801E1E1E),
                            )
                        )
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        Divider(modifier = Modifier
                            .border(width = 1.dp, color = Color(0xFFE0E0E0))
                            .fillMaxSize()
                            .height(0.2.dp))
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        Text(
                            text = "Menyediakan berbagai makanan dan minuman dengan citarasa Madura",
                            style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 17.64.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Color(0x801E1E1E),
                            )
                        )
                    }
                }
            }

        }
    }
}
