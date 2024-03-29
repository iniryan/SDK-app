package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.BottomNavigation
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.OrderResponse
import com.example.projectuasmobile.service.OrderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransactionPage(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

    val listOrder = remember { mutableStateListOf<OrderResponse>() }
    //val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val baseUrl = "https://api2.tnadam.me/api/"
    //LOKAL STRAPI
    //val baseUrl = "http://10.0.2.2:1337/api/"

    val checkStatus = remember {
        mutableStateOf(true)
    }
    Handler().postDelayed({
        checkStatus.value = true
    }, 5000)
    if (checkStatus.value && (preferencesManager.getData("boothID") != null || preferencesManager.getData("boothID") != "")) {
        val retrofit =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build().create(OrderService::class.java)
        val call = retrofit.getAllOrder(preferencesManager.getData("boothID"), "*", "id:desc")
        call.enqueue(object : Callback<ApiResponse<List<OrderResponse>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<OrderResponse>>>,
                response: Response<ApiResponse<List<OrderResponse>>>
            ) {
                if (response.isSuccessful) {
                    listOrder.clear()
                    response.body()?.data!!.forEach { orderResponse ->
                        listOrder.add(orderResponse)
                    }
                    checkStatus.value = false
                } else {
                    Toast.makeText(
                        context, "Error: ${response.code()} - ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<OrderResponse>>>, t: Throwable) {
                print(t.message)
            }

        })
    }
    val primaryColorOrg = Color(0xFFFF5F00)
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        },

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(vertical = 14.dp))
                Text(
                    text = "Daftar Riwayat Transaksi", style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        textAlign = TextAlign.Left,
                        color = primaryColorOrg,
                    ), modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.padding(vertical = 12.dp))
                LazyColumn {
                    if(listOrder.isNotEmpty()) {
                        listOrder.forEach { orderResponse ->
                            item {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        14.dp,
                                        Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color(0xFFF4F8FB),
                                            shape = RoundedCornerShape(size = 10.dp)
                                        )
                                        .padding(18.dp)
                                        .clickable { navController.navigate("detailTransaction/" + orderResponse.id) },
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            14.dp,
                                            Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.Start,
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                12.dp,
                                                Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.order),
                                                contentDescription = "image description",
                                                contentScale = ContentScale.None
                                            )
                                            Column {
                                                Text(
                                                    text = orderResponse.attributes.customerName,
                                                    style = TextStyle(
                                                        fontSize = 20.sp,
                                                        lineHeight = 28.sp,
                                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                        fontWeight = FontWeight(600),
                                                        color = Color(0xFF0B1527),
                                                    )
                                                )
                                                Text(
                                                    text = "Nomor Meja: " + orderResponse.attributes.tableNumber,
                                                    style = TextStyle(
                                                        fontSize = 14.sp,
                                                        lineHeight = 28.sp,
                                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                        fontWeight = FontWeight(600),
                                                        color = Color(0xFF0B1527),
                                                    )
                                                )
                                            }
                                        }
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(2.dp)
                                                .background(color = Color(0xFFFFFFFF))
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                60.dp,
                                                Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    6.dp,
                                                    Alignment.CenterHorizontally
                                                ),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                when (orderResponse.attributes.status) {
                                                    "pending" -> {
                                                        Image(
                                                            painter = painterResource(id = R.drawable.info_warning_min),
                                                            contentDescription = "image description",
                                                            contentScale = ContentScale.None,
                                                        )
                                                        Text(
                                                            text = "Antrian",
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                lineHeight = 16.sp,
                                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                                fontWeight = FontWeight(500),
                                                                color = Color(0xFF0B1527),
                                                            )
                                                        )
                                                    }
                                                    "proses" -> {
                                                        Image(
                                                            painter = painterResource(id = R.drawable.time_min),
                                                            contentDescription = "image description",
                                                            contentScale = ContentScale.None,
                                                        )
                                                        Text(
                                                            text = "Proses",
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                lineHeight = 16.sp,
                                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                                fontWeight = FontWeight(500),
                                                                color = Color(0xFF0B1527),
                                                            )
                                                        )
                                                    }
                                                    "bayar" -> {
                                                        Image(
                                                            painter = painterResource(id = R.drawable.time_min),
                                                            contentDescription = "image description",
                                                            contentScale = ContentScale.None,
                                                        )
                                                        Text(
                                                            text = "Pembayaran",
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                lineHeight = 16.sp,
                                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                                fontWeight = FontWeight(500),
                                                                color = Color(0xFF0B1527),
                                                            )
                                                        )
                                                    }
                                                    "tolak" -> {
                                                        Image(
                                                            painter = painterResource(id = R.drawable.info_min),
                                                            contentDescription = "image description",
                                                            contentScale = ContentScale.None,
                                                        )
                                                        Text(
                                                            text = "Ditolak",
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                lineHeight = 16.sp,
                                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                                fontWeight = FontWeight(500),
                                                                color = Color(0xFF0B1527),
                                                            )
                                                        )
                                                    }
                                                    else -> {
                                                        Image(
                                                            painter = painterResource(id = R.drawable.check_min),
                                                            contentDescription = "image description",
                                                            contentScale = ContentScale.None,
                                                        )
                                                        Text(
                                                            text = "Selesai",
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                lineHeight = 16.sp,
                                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                                fontWeight = FontWeight(500),
                                                                color = Color(0xFF0B1527),
                                                            )
                                                        )
                                                    }
                                                }
                                            }
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    6.dp,
                                                    Alignment.CenterHorizontally
                                                ),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "Transaksi: ",
                                                    style = TextStyle(
                                                        fontSize = 12.sp,
                                                        lineHeight = 16.sp,
                                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                        fontWeight = FontWeight(700),
                                                        color = Color(0xFF0B1527),
                                                    )
                                                )
                                                val dateF =
                                                    orderResponse.attributes.createdAt.split("T")
                                                val formatDate =
                                                    SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(
                                                        dateF[0]
                                                    )
                                                val dateNow = SimpleDateFormat(
                                                    "dd MMMM yyyy",
                                                    Locale.US
                                                ).format(formatDate!!)
                                                Text(
                                                    text = dateNow,
                                                    style = TextStyle(
                                                        fontSize = 12.sp,
                                                        lineHeight = 16.sp,
                                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                        fontWeight = FontWeight(500),
                                                        color = Color(0xFF0B1527),
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(vertical = 8.dp))
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


