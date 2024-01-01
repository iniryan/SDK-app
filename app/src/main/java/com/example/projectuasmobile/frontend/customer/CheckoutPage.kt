package com.example.projectuasmobile.frontend.customer

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
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
import coil.compose.rememberAsyncImagePainter
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.OrderData
import com.example.projectuasmobile.data.OrderDataWrapper
import com.example.projectuasmobile.data.OrderDetailsDataWrapper
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.OrderDetailsResponse
import com.example.projectuasmobile.response.OrderResponse
import com.example.projectuasmobile.service.OrderDetailsService
import com.example.projectuasmobile.service.OrderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun CheckOutPage(
    orderItems: List<OrderDetailsDataWrapper>?,
    navController: NavController,
    context: Context = LocalContext.current
) {
    println(orderItems)
    val baseUrl = "http://10.0.2.2:1337/api/"

    var total = 0
    val convertedTotal = remember { mutableStateOf("") }
    val nameField = remember { mutableStateOf(TextFieldValue("")) }
    val notesField = remember { mutableStateOf(TextFieldValue("")) }
    val tableField = remember { mutableStateOf(TextFieldValue("")) }
    val primaryColorOrg = Color(0xFFFF5F00)
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> uri?.let { selectedImageUri = it } }
    )

    Box(modifier = Modifier.fillMaxSize()) {

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
                    .height(36.dp)
                    .clickable { navController.navigate("boothHome") },
                painter = painterResource(id = R.drawable.backwhite),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Lengkapi Datamu",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 17.64.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                )
            )
            Text(
                text = "Nama",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 14.dp)
            )
            OutlinedTextField(
                value = nameField.value,
                onValueChange = {
                    nameField.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp,
                        color = primaryColorOrg,
                        shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Isikan namamu disini") }
            )
            Text(
                text = "Catatan",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 14.dp)
            )
            OutlinedTextField(
                value = notesField.value,
                onValueChange = {
                    notesField.value = it
                },
                singleLine = false,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp,
                        color = primaryColorOrg,
                        shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Tulis catatan untuk penjual jika ada") }
            )
            Text(
                text = "Nomor Meja",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 14.dp)
            )
            OutlinedTextField(
                value = tableField.value,
                onValueChange = {
                    tableField.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp,
                        color = primaryColorOrg,
                        shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Isi nomor meja atau dibungkus") }
            )
            Spacer(modifier = Modifier.padding(10.dp))

            LazyColumn {
                orderItems?.forEach { menuResponse ->
                    item {
                        Divider(
                            modifier = Modifier
                                .width(390.dp)
                                .height(1.dp)
                                .background(color = Color(0xFFEEEEEE))
                        )
                        Column(
                            modifier = Modifier
                                .width(390.dp)
                                .height(157.5.dp)
                                .background(color = Color(0xFFFFFFFF))
                                .padding(start = 16.dp, top = 14.dp, end = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(
                                16.dp, Alignment.CenterVertically
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(358.dp)
                                    .height(127.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top,
                            ) {
                                Column(
                                    modifier = Modifier
                                        .width(218.dp)
                                        .height(127.dp),
                                    verticalArrangement = Arrangement.spacedBy(
                                        4.dp, Alignment.Top
                                    ),
                                    horizontalAlignment = Alignment.Start,
                                ) {
//                                                    val img = menuResponse.attributes.foodImg?.data!!.forEach { img ->
//                                                        println(img.attributes.url)
//                                                    }
//                                                    Text(text = menuResponse.attributes.foodImg?.data?.get(0)?.attributes?.formats?.thumbnail?.url.toString())
//                                                    Text(text = menuResponse.attributes.foodImg?.data?.get(0)?.attributes?.url.toString())
                                    Text(
                                        text = menuResponse.orderDetailsData.foods.attributes.foodName,

                                        style = TextStyle(
                                            fontSize = 15.sp,
                                            lineHeight = 19.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                            color = Color(0xFF333333),
                                        )
                                    )
                                    Text(
                                        text = menuResponse.orderDetailsData.foods.attributes.foodDescription,

                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            lineHeight = 18.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                            color = Color(0xFF757575),
                                        )
                                    )
                                    Text(
                                        text = menuResponse.orderDetailsData.foods.attributes.foodPrice.toString(),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                            color = Color(0xFF333333),
                                        )
                                    )
                                    Row(
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                            .width(218.dp)
                                            .height(28.dp),
                                        horizontalArrangement = Arrangement.spacedBy(
                                            16.dp, Alignment.Start
                                        ),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        total += (menuResponse.orderDetailsData.foods.attributes.foodPrice * menuResponse.orderDetailsData.qty)
                                        convertedTotal.value = total.toString()
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                12.dp, Alignment.CenterHorizontally
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "Jumlah pesanan: " + menuResponse.orderDetailsData.qty.toString() + " porsi",

                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    lineHeight = 14.sp,
                                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                    color = Color(0xFF333333),
                                                )
                                            )
                                        }
                                    }
                                }
//                                println(menuResponse.orderDetailsData.foods)
                                val imgurl = menuResponse.orderDetailsData.foods.attributes.foodImg?.data?.attributes!!.url
                                Image(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(100.dp),
                                    contentScale = ContentScale.Crop,
                                    painter = rememberAsyncImagePainter("http://10.0.2.2:1337" +imgurl),
                                    contentDescription = "image description"
                                )
//                                Image(
//                                    modifier = Modifier
//                                        .width(100.dp)
//                                        .height(100.dp),
//                                    painter = painterResource(id = R.drawable.imgplaceholder),
//                                    contentDescription = "image description",
//                                    contentScale = ContentScale.Crop
//                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .width(358.dp)
                                    .height(0.5.dp)
                                    .background(color = Color(0xFFEEEEEE))
                            )
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row {
                Text(
                    text = "Total harga : ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )
//                orderItems?.forEach {
//                    it.orderDetailsData.foods.attributes.foodPrice.let { it1 ->
//                        it.orderDetailsData.qty.let { it2 ->
//                            total += (it1 * it2)
//                        }
//                    }
//                }
                Text(
                    text = "Rp"+convertedTotal.value,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    if (nameField.value.text.isEmpty() || tableField.value.text.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Field tidak boleh kosong",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@ElevatedButton
                    } else {
                        val retrofit = Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(OrderService::class.java)
                        val call = retrofit.addOrder(
                            OrderDataWrapper(
                                OrderData(
                                    customerName = nameField.value.text,
                                    tableNumber = tableField.value.text,
                                    notes = notesField.value.text,
                                    total = total,
                                    status = "pending"
                                )
                            )
                        )
                        call.enqueue(object : Callback<ApiResponse<OrderResponse>> {
                            override fun onResponse(
                                call: Call<ApiResponse<OrderResponse>>,
                                response: Response<ApiResponse<OrderResponse>>
                            ) {
                                if (response.code() == 200) {
                                    val retrofit2 =
                                        Retrofit.Builder().baseUrl(baseUrl)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build().create(OrderDetailsService::class.java)
                                    orderItems?.forEach {
                                        it.orderDetailsData.orderID = response.body()?.data?.id.toString()
                                        val call2 = retrofit2.addOrderDetails(it)
                                        call2.enqueue(object : Callback<ApiResponse<OrderDetailsResponse>> {
                                            override fun onResponse(
                                                call: Call<ApiResponse<OrderDetailsResponse>>,
                                                response: Response<ApiResponse<OrderDetailsResponse>>
                                            ) {
                                                if (response.code() == 200) {
                                                    Toast.makeText(
                                                        context,
                                                        "Berhasil menambahkan pesanan",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
//                                                    navController.navigate("homepage") {
//                                                        popUpTo("homepage") {
//                                                            inclusive = true
//                                                        }
//                                                    }
                                                } else if (response.code() == 400) {
                                                    Toast.makeText(
                                                        context,
                                                        "Error: ${response.code()} - ${response.message()}",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }

                                            override fun onFailure(
                                                call: Call<ApiResponse<OrderDetailsResponse>>,
                                                t: Throwable
                                            ) {
                                                print(t.message)
                                            }

                                        })
                                    }
                                } else if (response.code() == 400) {
                                    Toast.makeText(
                                        context,
                                        "Error: ${response.code()} - ${response.message()}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(
                                call: Call<ApiResponse<OrderResponse>>,
                                t: Throwable
                            ) {
                                print(t.message)
                            }

                        })
                    }


//                    val retrofit =
//                        Retrofit.Builder().baseUrl(baseUrl)
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .build().create(OrderDetailsService::class.java)
//                    orderItems?.forEach {
//                        val call = retrofit.addOrderDetails(it)
//                        call.enqueue(object : Callback<OrderDetailsResponse> {
//                            override fun onResponse(
//                                call: Call<OrderDetailsResponse>,
//                                response: Response<OrderDetailsResponse>
//                            ) {
//                                if (response.code() == 200) {
//                                    Toast.makeText(
//                                        context,
//                                        "Berhasil menambahkan pesanan",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                } else if (response.code() == 400) {
//                                    Toast.makeText(
//                                        context,
//                                        "Error: ${response.code()} - ${response.message()}",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }
//
//                            override fun onFailure(
//                                call: Call<OrderDetailsResponse>,
//                                t: Throwable
//                            ) {
//                                print(t.message)
//                            }
//
//                        })
//                    }

                }
            )

            {
                Text(
                    text = "Pesan Menu",
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
}