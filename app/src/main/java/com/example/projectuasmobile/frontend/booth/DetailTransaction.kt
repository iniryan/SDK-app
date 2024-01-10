package com.example.projectuasmobile.frontend.booth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.UpdateStatusOrder
import com.example.projectuasmobile.data.UpdateStatusWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.OrderResponse
import com.example.projectuasmobile.service.OrderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailTransaction(
    navController: NavController,
    id: String?,
    context: Context = LocalContext.current
) {
    val primaryColorOrg = Color(0xFFFF5F00)
    val transactionID = remember { mutableStateOf(id ?: "") }
    val customerName = remember { mutableStateOf("") }
    val customerNumber = remember { mutableStateOf("") }
    val tableNumber = remember { mutableStateOf("") }
    val notes = remember { mutableStateOf("") }
    val total = remember { mutableStateOf("") }
    val status = remember { mutableStateOf("") }
    val alasanTolak = remember { mutableStateOf("") }
    val transactionTime = remember { mutableStateOf("") }

    val baseUrl = "https://api2.tnadam.me/api/"
    //LOKAL STRAPI
    //val baseUrl = "http://10.0.2.2:1337/api/"
    val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(OrderService::class.java)
    val call = retrofit.getOrderById(transactionID.value, "*")
    call.enqueue(object : Callback<ApiResponse<List<OrderResponse>>> {
        override fun onResponse(
            call: Call<ApiResponse<List<OrderResponse>>>,
            response: Response<ApiResponse<List<OrderResponse>>>
        ) {
            if (response.isSuccessful) {
                val resp = response.body()?.data
                resp?.let { dataList ->
                    if (dataList.isNotEmpty()) {
                        val data = dataList[0]
                        transactionID.value = data.id.toString()
                        customerName.value = data.attributes.customerName
                        customerNumber.value = data.attributes.customerNumber
                        tableNumber.value = data.attributes.tableNumber
                        notes.value = data.attributes.notes
                        total.value = data.attributes.total.toString()
                        status.value = data.attributes.status
                        alasanTolak.value = data.attributes.alasanTolak
                        transactionTime.value = data.attributes.createdAt
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

        override fun onFailure(call: Call<ApiResponse<List<OrderResponse>>>, t: Throwable) {
            print(t.message)
        }
    })

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(color = Color(0xFFFFFFFF))
        ) {
            IconButton(modifier = Modifier
                .padding(bottom = 16.dp)
                .background(
                    color = primaryColorOrg, shape = RoundedCornerShape(100.dp)
                ), onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    modifier = Modifier.size(25.dp),
                    tint = Color.White
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                when (status.value) {
                    "pending" -> {
                        Image(
                            painter = painterResource(id = R.drawable.info_warning),
                            contentDescription = "image description",
                            contentScale = ContentScale.None,
                        )
                        Column {
                            Text(
                                text = "Dalam Antrian!",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF212529),
                                )
                            )
                            Text(
                                text = "Pesanan customer sedang dalam antrian",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF212529),

                                    )
                            )
                        }
                    }
                    "proses" -> {
                        Image(
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "image description",
                            contentScale = ContentScale.None,
                        )
                        Column {
                            Text(
                                text = "Sedang diproses!",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF212529),
                                )
                            )
                            Text(
                                text = "Pesanan customer sedang diproses",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF212529),

                                    )
                            )
                        }
                    }
                    "bayar" -> {
                        Image(
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "image description",
                            contentScale = ContentScale.None,
                        )
                        Column {
                            Text(
                                text = "Menunggu Konfirmasi Pembayaran!",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF212529),
                                )
                            )
                            Text(
                                text = "Menunggu pembayaran dari customer",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF212529),

                                    )
                            )
                        }
                    }
                    "tolak" -> {
                        Image(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "image description",
                            contentScale = ContentScale.None,
                        )
                        Column {
                            Text(
                                text = "Pesanan ditolak!",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF212529),
                                )
                            )
                            Text(
                                text = "Pesanan customer ditolak karena " + if(alasanTolak.value == null) "'tidak ada alasan'" else alasanTolak.value,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF212529),

                                    )
                            )
                        }
                    }
                    else -> {
                        Image(
                            painter = painterResource(id = R.drawable.check),
                            contentDescription = "image description",
                            contentScale = ContentScale.None,
                        )
                        Column {
                            Text(
                                text = "Pesanan Selesai!",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF212529),
                                )
                            )
                            Text(
                                text = "Pesanan customer telah selesai",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF212529),

                                    )
                            )
                        }
                    }
                }
            }
            Text(
                text = "Tanggal Transaksi: "+ transactionTime.value,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
            )
            Text(
                text = "Detail Transaksi",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Nama Customer: " + customerName.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF212529),
                    )
                )
                Text(
                    text = "Telepon: " + customerNumber.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF212529),
                    )
                )
                Text(
                    text = "Table number: " + tableNumber.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF212529),
                    )
                )
                Text(
                    text = "Catatan: " + notes.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF212529),
                    )
                )
            }
            Text(
                text = "Order Item",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(vertical = 12.dp)
            )
//                Lazycolumn
            Text(
                text = "Order Summary",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF212529),
                ),
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = "Total",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF212529),
                        )
                    )
                    Text(
                        text = "Rp" + total.value,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF212529),
                            textAlign = TextAlign.Right,
                            letterSpacing = 0.3.sp,
                        )
                    )
                }
            }
            when (status.value) {
                "pending" -> {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ElevatedButton(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .height(64.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = primaryColorOrg
                            ),
                            shape = RoundedCornerShape(8.dp),
                            onClick = {
                                val retrofitUpdate = Retrofit.Builder().baseUrl(baseUrl)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build().create(OrderService::class.java)
                                val callUpdate = retrofitUpdate.updateStatusOrder(transactionID.value, UpdateStatusWrapper(UpdateStatusOrder("proses")))
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
                        )
                        {
                            Text(
                                text = "Terima Pesanan",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                        ElevatedButton(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .height(64.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(8.dp),
                            onClick = {
                                navController.navigate("refuseOrder/"+transactionID.value)
                            }
                        )
                        {
                            Text(
                                text = "Tolak Pesanan",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                    }
                }
                "bayar" -> (
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ElevatedButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                                    .height(64.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = primaryColorOrg
                                ),
                                shape = RoundedCornerShape(8.dp),
                                onClick = {
                                    val retrofitUpdateProses = Retrofit.Builder().baseUrl(baseUrl)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build().create(OrderService::class.java)
                                    val callUpdateProses = retrofitUpdateProses.updateStatusOrder(transactionID.value, UpdateStatusWrapper(UpdateStatusOrder("selesai")))
                                    callUpdateProses.enqueue(object : Callback<ApiResponse<OrderResponse>> {
                                        override fun onResponse(
                                            callUpdateProses: Call<ApiResponse<OrderResponse>>,
                                            retrofitUpdateProses: Response<ApiResponse<OrderResponse>>
                                        ) {
                                            if (retrofitUpdateProses.isSuccessful) {
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

                                        override fun onFailure(callUpdateProses: Call<ApiResponse<OrderResponse>>, t: Throwable) {
                                            print(t.message)
                                        }

                                    })
                                }
                            )
                            {
                                Text(
                                    text = "Pesanan Selesai, Konfirmasi",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }
                        }
                        )
                "proses" -> {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 60.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Sedang diproses....",
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



