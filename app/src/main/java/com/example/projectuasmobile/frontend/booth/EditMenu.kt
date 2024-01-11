package com.example.projectuasmobile.frontend.booth

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.FoodData
import com.example.projectuasmobile.data.FoodDataWrapper
import com.example.projectuasmobile.response.FoodResponse
import com.example.projectuasmobile.service.FoodService
import com.example.projectuasmobile.service.ImgService
import com.example.projectuasmobile.service.UploadResponseList
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

@Composable
fun EditMenu(
    navController: NavController,
    foodID: String?,
    foodName: String?,
    foodDescription: String?,
    foodPrice: String?,
    newUrl: String?,
    context: Context = LocalContext.current
) {
    val foodNameField = remember { mutableStateOf(foodName ?: "") }
    val foodId = remember { mutableStateOf(foodID ?: "") }
    val foodDescriptionField = remember { mutableStateOf(foodDescription ?: "") }
    val foodPriceField = remember { mutableStateOf(foodPrice ?: "") }

    //LOKAL STRAPI
    //val baseUrl = "http://10.0.2.2:1337/api/"
    val baseUrl = "https://api2.tnadam.me/api/"

    val currentValue = newUrl ?: ""
    val editUrl = currentValue.replace("::uploads::", "/uploads/")
    val preferencesManager = remember { PreferencesManager(context = context) }
    val boothId = preferencesManager.getData("boothID")
    val primaryColorOrg = Color(0xFFFF5F00)

    var selectedImageFile by remember { mutableStateOf<File?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val resolver = context.contentResolver
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                selectedImageUri = it
                resolver.openInputStream(selectedImageUri!!)?.let { inputStream ->
                    val originalFileName = context.contentResolver.query(
                        selectedImageUri!!, null, null, null, null
                    )?.use { cursor ->
                        if (cursor.moveToFirst()) {
                            val displayNameIndex =
                                cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                            if (displayNameIndex != -1) {
                                cursor.getString(displayNameIndex)
                            } else {
                                null
                            }
                        } else {
                            null
                        }
                    }
                    val file = File(context.cacheDir, originalFileName ?: "temp_img.jpg")
                    Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING)
                    selectedImageFile = file
                }
            }
        })

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
                onClick = { navController.navigateUp() }
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
                text = "Form Edit Menu", style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = primaryColorOrg,
                    textAlign = TextAlign.Left
                ), modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Nama Menu", style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 14.dp)
            )
            OutlinedTextField(value = foodNameField.value,
                onValueChange = {
                    foodNameField.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = primaryColorOrg, shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Contoh: Penyetan Ayam") })
            Text(
                text = "Deskripsi Menu", style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 14.dp)
            )
            OutlinedTextField(value = foodDescriptionField.value,
                onValueChange = {
                    foodDescriptionField.value = it
                },
                singleLine = false,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = primaryColorOrg, shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Contoh: Isian menu adalah Nasi + Ayam + Terong + Tahu + Tempe + Sambal") })
            Text(
                text = "Harga Menu", style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 14.dp)
            )
            OutlinedTextField(value = foodPriceField.value,
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
                        width = 1.5.dp, color = primaryColorOrg, shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Contoh: 10000") })
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
                    if (selectedImageUri != null) {
                        Image(
                            painter = rememberImagePainter(data = selectedImageUri, builder = {
                                transformations(CircleCropTransformation())
                            }),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(8.dp))
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
//                            painter = rememberAsyncImagePainter("http://10.0.2.2:1337$editUrl"),
                            painter = rememberAsyncImagePainter("https://api2.tnadam.me$editUrl"),
                            contentDescription = "image description"
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                if (selectedImageUri != null) {
                    IconButton(
                        onClick = { selectedImageUri = null }, modifier = Modifier.size(48.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear Image")
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
                .padding(2.dp)
                .height(48.dp), colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
            ), shape = RoundedCornerShape(8.dp), onClick = {
                val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(FoodService::class.java)
//                try {
                    val price = foodPriceField.value.toInt()
                    val foodData = FoodDataWrapper(
                        FoodData(
                            foodNameField.value,
                            foodDescriptionField.value,
                            price,
                            boothId.toInt()
                        )
                    )
                    val json = Gson().toJson(foodData)
                    println("Request JSON: $json")
                    val call = retrofit.update(foodID, foodData)

                    call.enqueue(object : Callback<FoodResponse> {
                        override fun onResponse(
                            call: Call<FoodResponse>, response: Response<FoodResponse>
                        ) {
                            if (response.isSuccessful) {
                                if(selectedImageFile != null) {
                                    val file = selectedImageFile
                                    val mimeType =
                                        MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                                            file!!.extension
                                        )
                                    val refRequestBody =
                                        "api::food.food".toRequestBody("multipart/form-data".toMediaTypeOrNull())
                                    val refIdRequestBody = foodId.value
                                        .toRequestBody("multipart/form-data".toMediaTypeOrNull())
                                    val fieldRequestBody =
                                        "foodImg".toRequestBody("multipart/form-data".toMediaTypeOrNull())
                                    val fileRequestBody = MultipartBody.Part.createFormData(
                                        "files",
                                        file.name,
                                        file.asRequestBody(mimeType?.toMediaTypeOrNull())
                                    )

                                    val retrofit2 = Retrofit.Builder().baseUrl(baseUrl)
                                        .addConverterFactory(GsonConverterFactory.create()).client(
                                            OkHttpClient.Builder().addInterceptor(
                                                HttpLoggingInterceptor().setLevel(
                                                    HttpLoggingInterceptor.Level.BODY
                                                )
                                            ).build()
                                        )
                                        .build().create(ImgService::class.java)
                                    val call2 = retrofit2.uploadImage(
                                        refRequestBody,
                                        refIdRequestBody,
                                        fieldRequestBody,
                                        fileRequestBody
                                    )
                                    call2.enqueue(object : Callback<UploadResponseList> {
                                        override fun onResponse(
                                            call12: Call<UploadResponseList>,
                                            response12: Response<UploadResponseList>
                                        ) {
                                            if (response12.isSuccessful) {
                                                Toast.makeText(
                                                    context,
                                                    "Berhasil mengubah data menu",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                Handler().postDelayed({
                                                    navController.navigate("menu")
                                                }, 5000)
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Error: ${response.code()} - ${response.message()}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                        override fun onFailure(
                                            call12: Call<UploadResponseList>, t: Throwable
                                        ) {
                                            Toast.makeText(
                                                context,
                                                "Error: ${response.code()} - ${response.message()}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    })
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Berhasil mengubah data menu",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Handler().postDelayed({
                                        navController.navigate("menu")
                                    }, 5000)
                                }
                            } else {
                                Toast.makeText(
                                    context, "Error: ${response.code()}", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    })
//                } catch (e: NumberFormatException) {
//                    Toast.makeText(context, "Error: Invalid price format", Toast.LENGTH_SHORT)
//                        .show()
//                }
            })

            {
                Text(
                    text = "Edit Menu", style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
                .padding(2.dp)
                .height(48.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
            ), shape = RoundedCornerShape(8.dp), onClick = {
                val retrofitDelete = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(FoodService::class.java)
                val callDelete = retrofitDelete.delete(foodID.toString().toInt())
                callDelete.enqueue(object : Callback<FoodResponse> {
                    override fun onResponse(
                        call: Call<FoodResponse>, response: Response<FoodResponse>
                    ) {
                        if (response.isSuccessful) {
                            navController.navigate("menu")
                        } else {
                            Toast.makeText(
                                context,
                                "Error: ${response.code()} - ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                    override fun onFailure(
                        call: Call<FoodResponse>, t: Throwable
                    ) {
                        print(t.message)
                    }

                })
            }) {
                Text(
                    text = "Hapus Menu", style = TextStyle(
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
