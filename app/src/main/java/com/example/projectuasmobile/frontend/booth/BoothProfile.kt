package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
fun BoothProfile(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val fullname = preferencesManager.getData("fullname")
    val owner = preferencesManager.getData("userID")
    val email = preferencesManager.getData("email")

    val boothName = remember { mutableStateOf("") }
    val boothDesc = remember { mutableStateOf("") }
    val open = remember { mutableStateOf(true) }
    val boothID = remember { mutableIntStateOf(0) }

    val baseUrl = "http://10.0.2.2:1337/api/"
    val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(BoothService::class.java)
    val call = retrofit.getProfile(owner, "*")
    call.enqueue(object : Callback<ApiResponse<List<BoothResponse>>> {
        override fun onResponse(
            call: Call<ApiResponse<List<BoothResponse>>>,
            response: Response<ApiResponse<List<BoothResponse>>>
        ) {
            if (response.isSuccessful) {
                val resp = response.body()?.data
                resp?.let { dataList ->
                    if (dataList.isNotEmpty()) {
                        val id = dataList[0].id
                        boothID.intValue = id
                        val boothNames = dataList[0].attributes.boothName
                        boothName.value = boothNames
                        val boothDescription = dataList[0].attributes.boothDescription
                        boothDesc.value = boothDescription
                        val openBooth = dataList[0].attributes.open
                        open.value = openBooth
                    }
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
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("editProfile/" + boothID.intValue + "/" + boothName.value + "/" + boothDesc.value + "/" + open.value.toString())
            }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        },

        ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(114.dp)
                    .height(114.dp),
                painter = painterResource(id = R.drawable.profilepict),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = fullname, style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            )
            Text(
                text = email, style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Profile Booth", style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF5F00),
                )
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "Nama Kios", style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                )
            )
            Text(
                text = boothName.value, style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = Color(0xFFFF5F00),
                )
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(
                text = "Deskripsi Booth", style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                )
            )
            Text(
                text = boothDesc.value, style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = Color(0xFFFF5F00),
                )
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(
                text = "Status Booth", style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                )
            )
            Text(
                text = if (open.value) "Buka" else "Tutup", style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFF5F00),
                )
            )


        }
        Button(
            onClick = {
                preferencesManager.clearData()
                navController.navigate("rolepick")
            }, modifier = Modifier.padding(start = 320.dp, top = 12.dp)
        ) {
            Text(text = "Logout")
        }
    }
}

