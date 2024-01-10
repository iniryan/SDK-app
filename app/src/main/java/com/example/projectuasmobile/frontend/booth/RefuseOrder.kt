package com.example.projectuasmobile.frontend.booth

import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.RefuseOrder
import com.example.projectuasmobile.data.RefuseOrderWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.OrderResponse
import com.example.projectuasmobile.service.OrderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun RefuseOrder(navController: NavController, id: String?, context: Context = LocalContext.current) {
    val primaryColorOrg = Color(0xFFFF5F00)
    val reason = remember { mutableStateOf(TextFieldValue("")) }
    val transID = remember { mutableStateOf(id) }
    val baseUrl = "https://api2.tnadam.me/api/"
    //LOKAL STRAPI
    //val baseUrl = "http://10.0.2.2:1337/api/"

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Alasan Penolakan Pesanan", style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Center,
                    ), modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 14.dp)
                )
                OutlinedTextField(value = reason.value,
                    onValueChange = {
                        reason.value = it
                    },
                    singleLine = false,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(2.dp)
                        .border(
                            width = 1.5.dp,
                            color = primaryColorOrg,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = { Text(text = "Tulis alasan penolakan pesanan") })
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                ElevatedButton(modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .height(64.dp), colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                ), shape = RoundedCornerShape(8.dp), onClick = {
                    if (reason.value.text.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Field tidak boleh kosong",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@ElevatedButton
                    } else {
                        val retrofitUpdate = Retrofit.Builder().baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(OrderService::class.java)
                        val callUpdate = retrofitUpdate.refuseOrder(transID.value,
                            RefuseOrderWrapper(
                                RefuseOrder(
                                    "tolak",
                                    reason.value.text
                                )
                            )
                        )
                        callUpdate.enqueue(object : Callback<ApiResponse<OrderResponse>> {
                            override fun onResponse(
                                callUpdate: Call<ApiResponse<OrderResponse>>,
                                responseUpdate: Response<ApiResponse<OrderResponse>>
                            ) {
                                if (responseUpdate.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Berhasil ubah status pesanan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Handler().postDelayed({
                                        navController.navigate("boothHome")
                                    }, 5000)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(callUpdate: Call<ApiResponse<OrderResponse>>, t: Throwable) {
                                print(t.message)
                            }

                        })
                    }
                })
                {
                    Text(
                        text = "Tolak Pesanan", style = TextStyle(
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
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(
            modifier = Modifier
                .padding(top = 12.dp, end = 12.dp)
                .shadow(10.dp, RoundedCornerShape(100.dp))
                .background(
                    color = Color(0xFFFF5F00)
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
            text = "Alasan Tolak Pesanan", style = TextStyle(
                fontSize = 26.sp,
                lineHeight = 36.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = Color(0xFF333333),
            ),
            modifier = Modifier.padding(top = 16.dp, end = 12.dp)
        )
    }
}