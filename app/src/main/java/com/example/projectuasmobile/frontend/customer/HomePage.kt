package com.example.projectuasmobile.frontend.customer

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.projectuasmobile.BottomNavCustomer
import com.example.projectuasmobile.R
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.BoothResponse
import com.example.projectuasmobile.service.BoothService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController, context: Context = LocalContext.current) {
    val primaryColorOrg = Color(0xFFFF5F00)
    val searchField = remember { mutableStateOf(TextFieldValue("")) }
    val imageList = listOf(
        R.drawable.makanankat,
        R.drawable.rotikat,
        R.drawable.juskat,
        R.drawable.cepatsajikat,
        R.drawable.baratkat,
        R.drawable.nasikat,
        R.drawable.seafoodkat
    )

    val listBooth = remember { mutableStateListOf<BoothResponse>() }
    //LOKAL STRAPI
    //val baseUrl = "http://10.0.2.2:1337/api/"
    val baseUrl = "https://api2.tnadam.me/api/"

    val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(BoothService::class.java)
    val call = retrofit.getAllBooth(searchField.value.text, true, "*")
    call.enqueue(object : Callback<ApiResponse<List<BoothResponse>>> {
        override fun onResponse(
            call: Call<ApiResponse<List<BoothResponse>>>,
            response: Response<ApiResponse<List<BoothResponse>>>
        ) {
            if (response.isSuccessful) {
                listBooth.clear()
                response.body()?.data!!.forEach { booth: BoothResponse ->
                    listBooth.add(booth)
                }
            } else {
                Toast.makeText(
                    context,
                    "Error: ${response.code()} - ${response.message()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<ApiResponse<List<BoothResponse>>>, t: Throwable) {
            print(t.message)
        }
    })

    Scaffold(
        bottomBar = {
            BottomNavCustomer(navController = navController)
        },

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(top = 12.dp, end = 12.dp)
                            .background(
                                color = Color(0xFFFF5F00),
                                shape = RoundedCornerShape(100.dp)
                            ),
                        onClick = {
                            navController.navigate("rolepick")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            modifier = Modifier.size(25.dp),
                            tint = Color.White
                        )
                    }
                    OutlinedTextField(
                        value = searchField.value,
                        onValueChange = {
                            searchField.value = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 12.dp),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "search",
                                tint = primaryColorOrg,
                            )
                        },
                        placeholder = { Text(text = "Pencarian", color = primaryColorOrg) }
                    )
                }
                Spacer(modifier = Modifier.padding(top = 14.dp, bottom = 14.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = primaryColorOrg,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "Rasa Lezat di Ujung Jari! Pesan Sekarang dan Sajikan Kelezatan dalam Sekejap.",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 28.8.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                color = Color.White,
                                letterSpacing = 0.18.sp,
                            )
                        )
                        Text(
                            text = "#SDKApp",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 18.2.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Color.White,
                                letterSpacing = 0.25.sp,
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 14.dp, bottom = 14.dp))
                Text(
                    text = "Sedia Aneka",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFF5F00),
                    ), modifier = Modifier.align(Alignment.Start)
                )
                LazyRow {
                    items(imageList) { image ->
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "image description",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                                .padding(all = 12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
                Text(
                    text = "Daftar Booth Tersedia", style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        textAlign = TextAlign.Left,
                        color = Color(0xFFFF5F00),
                    ), modifier = Modifier.align(Alignment.Start).padding(bottom = 16.dp)
                )
                LazyColumn {
                    if(listBooth.isNotEmpty()) {
                        listBooth.forEach { booth ->
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(
                                        12.dp,
                                        Alignment.Start
                                    ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 14.dp)
                                        .background(
                                            color = Color(0xFFF4F8FB),
                                            shape = RoundedCornerShape(size = 10.dp)
                                        )
                                        .clickable { navController.navigate("detailBooth/" + booth.id + "/" + booth.attributes.boothName + "/" + booth.attributes.boothDescription) }
                                ) {
                                    Image(
//                                    painter = rememberAsyncImagePainter("http://10.0.2.2:1337" + booth.attributes.boothImg?.data?.attributes!!.url),
                                        painter = rememberAsyncImagePainter("https://api2.tnadam.me" + booth.attributes.boothImg?.data?.attributes!!.url),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(100.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            1.dp,
                                            Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.Start,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp)
                                    ) {
                                        Text(
                                            text = booth.attributes.boothName, style = TextStyle(
                                                fontSize = 18.sp,
                                                lineHeight = 17.64.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                                color = Color(0xFF0B1527),
                                            )
                                        )
                                        Text(
                                            text = "Status Kios: " + if (booth.attributes.open) "Buka" else "Tutup",
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                lineHeight = 17.64.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF0B1527),
                                            )
                                        )
                                        Spacer(modifier = Modifier.padding(top = 2.dp))
                                        Divider(
                                            modifier = Modifier
                                                .border(width = 1.dp, color = Color(0xFFE0E0E0))
                                                .fillMaxSize()
                                                .height(1.dp)
                                        )
                                        Spacer(modifier = Modifier.padding(top = 2.dp))
                                        Text(
                                            text = booth.attributes.boothDescription,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                lineHeight = 17.64.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                color = Color(0xFF0B1527),
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.padding(vertical = 60.dp))
                        }
                    } else {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Column(
                                    modifier = Modifier.padding(50.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        text = "Tidak ada Booth/Kios yang sedang buka",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                            color = Color(0xFF495057),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}