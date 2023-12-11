package com.example.projectuasmobile.frontend.auth

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.BoothDataWrapper
import com.example.projectuasmobile.data.RegisterBoothData
import com.example.projectuasmobile.response.Booth
import com.example.projectuasmobile.service.BoothService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun RegisterBooth(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val primaryColorOrg = Color(0xFFFF5F00)
    val boothName = remember { mutableStateOf(TextFieldValue("")) }
    val boothDescription = remember { mutableStateOf(TextFieldValue("")) }

    val baseUrl = "http://10.0.2.2:1337/api/"
    //KALAU TIDAK DI EMULATOR
    //val baseUrl = "http://10.217.17.11:1337/api/"

    var jwt by remember { mutableStateOf("") }
    jwt = preferencesManager.getData("jwt")
    val userName = preferencesManager.getData("username")
    val userID = preferencesManager.getData("userID")

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp),
                painter = painterResource(id = R.drawable.backwhite),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "SDK-App",
                style = TextStyle(
                    fontSize = 54.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 126.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Daftar Boothmu Untuk Mulai Jualan",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = boothName.value,
                onValueChange = {
                    boothName.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .width(340.dp)
                    .height(54.dp)
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00), shape = RoundedCornerShape(24.dp)
                    ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.profilee),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                },
                placeholder = {
                    Text(
                        text = "Booth Name",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                })
            Spacer(modifier = Modifier.height(28.dp))
            OutlinedTextField(value = userName,
                onValueChange = { },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .width(340.dp)
                    .height(54.dp)
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00), shape = RoundedCornerShape(24.dp)
                    ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.profilee),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                },
                placeholder = {
                    Text(
                        text = "Booth Name",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                })
            Spacer(modifier = Modifier.height(28.dp))
            OutlinedTextField(value = boothDescription.value,
                onValueChange = {
                    boothDescription.value = it
                },
                singleLine = false,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .width(340.dp)
                    .height(54.dp)
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00), shape = RoundedCornerShape(24.dp)
                    ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.profilee),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                },
                placeholder = {
                    Text(
                        text = "Booth Description",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                })
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = {
                    val retrofit =
                        Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(BoothService::class.java)
                    val createBooth = BoothDataWrapper(
                        RegisterBoothData(
                            boothName = boothName.value.text,
                            boothDescription = boothDescription.value.text,
                            owner = userID.toInt()
                        )
                    )
                    val json = Gson().toJson(createBooth)
                    println("Request JSON: $json")
                    val call = retrofit.createBooth(createBooth)
                    call.enqueue(object : Callback<Booth> {
                        override fun onResponse(
                            call: Call<Booth>,
                            response: Response<Booth>
                        ) {
                            if (response.isSuccessful) {
                                val boothResponse = response.body()
                                if (boothResponse != null) {
                                    preferencesManager.saveData("boothName", boothName.value.text)
                                    preferencesManager.saveData("boothDescription", boothDescription.value.text)
                                    preferencesManager.saveData("owner", userID)
                                    print("Successful register")
                                    navController.navigate("boothHome")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error: Response body is null",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Error: ${response.code()} - ${response.message()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Booth>, t: Throwable) {
                            print(t.message)
                        }
                    })
                }, modifier = Modifier
                    .width(217.dp)
                    .height(64.dp)
                    .padding(start = 10.dp, top = 12.dp, end = 10.dp, bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColorOrg)
            ) {
                Text(

                    text = "DAFTAR BOOTH",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(600),
                        color = Color.White,
                    )
                )
            }
        }
    }

}