package com.example.projectuasmobile.frontend.booth

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.FoodData
import com.example.projectuasmobile.data.FoodDataWrapper
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.service.FoodService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AddMenu(navController: NavController, context: Context = LocalContext.current) {
    val foodNameField = remember { mutableStateOf(TextFieldValue("")) }
    val foodDescriptionField = remember { mutableStateOf(TextFieldValue("")) }
    val foodPriceField = remember { mutableStateOf(TextFieldValue("")) }

    val preferencesManager = remember { PreferencesManager(context = context) }
    val boothId = preferencesManager.getData("boothID")
    val primaryColorOrg = Color(0xFFFF5F00)

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val pickImageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
            onResult = { uri: Uri? -> uri?.let { selectedImageUri = it } })


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
                    .background(
                        color = Color(0xFFFF5F00),
                        shape = RoundedCornerShape(100.dp)
                    ),
                onClick = { navController.navigate("menu") }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    modifier = Modifier.size(25.dp),
                    tint = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Form Tambah Menu",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = primaryColorOrg,
                    textAlign = TextAlign.Left
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Nama Menu",
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
                value = foodNameField.value,
                onValueChange = {
                    foodNameField.value = it
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
                placeholder = { Text(text = "Contoh: Penyetan Ayam") }
            )
            Text(
                text = "Deskripsi Menu",
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
                value = foodDescriptionField.value,
                onValueChange = {
                    foodDescriptionField.value = it
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
                placeholder = { Text(text = "Contoh: Isian menu adalah Nasi + Ayam + Terong + Tahu + Tempe + Sambal") }
            )
            Text(
                text = "Harga Menu",
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
                value = foodPriceField.value,
                onValueChange = {
                    foodPriceField.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                placeholder = { Text(text = "Contoh: 10000") }
            )
            Text(
                text = "Foto Menu", style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 14.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            ) {
                Box(modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clickable { pickImageLauncher.launch("image/*") }
                    .border(
                        width = 1.5.dp,
                        color = primaryColorOrg,
                        shape = RoundedCornerShape(8.dp)
                    ), contentAlignment = Alignment.Center) {
                    // Display the selected image or an icon if no image is selected
                    if (selectedImageUri != null) {
                        // Display the selected image using rememberImagePainter from Coil
                        Image(
                            painter = rememberImagePainter(data = selectedImageUri, builder = {
                                // Optional: Apply transformations, e.g., CircleCropTransformation
                                transformations(CircleCropTransformation())
                            }),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(8.dp))
                        )
                    } else {
                        // Display an icon to prompt the user to select an image
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Add Photo",
                            tint = primaryColorOrg
                        )
                    }
                }


                Spacer(modifier = Modifier.width(8.dp))

                // Clear button to remove the selected image
                if (selectedImageUri != null) {
                    IconButton(
                        onClick = { selectedImageUri = null }, modifier = Modifier.size(48.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear Image")
                    }
                }
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
                    val baseUrl = "http://10.0.2.2:1337/api/"
                    val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(FoodService::class.java)
                    try {
                        val price = foodPriceField.value.text.toInt()
                        val foodData = FoodDataWrapper(
                            FoodData(
                                foodNameField.value.text,
                                foodDescriptionField.value.text,
                                price,
                                boothId.toInt()
                            )
                        )
                        val json = Gson().toJson(foodData)
                        println("Request JSON: $json")
                        val call = retrofit.addFood(foodData)

                        call.enqueue(object : Callback<FoodResponse> {
                            override fun onResponse(
                                call: Call<FoodResponse>,
                                response: Response<FoodResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val foodResponse = response.body()
                                    if (foodResponse != null) {
                                        navController.navigate("menu")
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
                                        "Error: ${response.code()}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } catch (e: NumberFormatException) {
                        Toast.makeText(context, "Error: Invalid price format", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )

            {
                Text(
                    text = "Tambah Menu",
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