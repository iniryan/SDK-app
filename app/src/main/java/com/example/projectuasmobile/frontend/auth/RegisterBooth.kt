package com.example.projectuasmobile.frontend.auth

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.BoothDataWrapper
import com.example.projectuasmobile.data.RegisterBoothData
import com.example.projectuasmobile.response.ApiResponse
import com.example.projectuasmobile.response.BoothResponse
import com.example.projectuasmobile.service.BoothService
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
fun RegisterBooth(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val primaryColorOrg = Color(0xFFFF5F00)
    val boothName = remember { mutableStateOf(TextFieldValue("")) }
    val boothDescription = remember { mutableStateOf(TextFieldValue("")) }

//    val baseUrl = "https://api2.tnadam.me/api/"
    val baseUrl = "http://10.0.2.2:1337/api/"
    var jwt by remember { mutableStateOf("") }
    jwt = preferencesManager.getData("jwt")
    val userName = preferencesManager.getData("username")
    val userID = preferencesManager.getData("userID")

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
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
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
            verticalArrangement = Arrangement.Center,
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
                    .height(64.dp)
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00)
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
                    .height(64.dp)
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00)
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
                    .height(120.dp)
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00)
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
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Add Photo",
                            tint = primaryColorOrg
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

            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = {
                    if (selectedImageFile == null) {
                        Toast.makeText(context, "Error: Image is required", Toast.LENGTH_SHORT).show()
                        return@Button
                    } else if(boothName.value.text.isEmpty() || boothDescription.value.text.isEmpty()) {
                        Toast.makeText(context, "Error: Field is required", Toast.LENGTH_SHORT).show()
                        return@Button
                    } else {
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
                            open = true,
                            owner = userID.toInt(),
                        )
                    )
                    val json = Gson().toJson(createBooth)
                    println("Request JSON: $json")
                    val call = retrofit.createBooth(createBooth)
                    call.enqueue(object : Callback<ApiResponse<BoothResponse>> {
                        override fun onResponse(
                            call: Call<ApiResponse<BoothResponse>>,
                            response: Response<ApiResponse<BoothResponse>>
                        ) {
                            if (response.isSuccessful) {
                                val boothResponse = response.body()
                                if (boothResponse != null) {

                                    val id = response.body()!!.data!!.id
                                    val file = selectedImageFile
                                    val mimeType =
                                        MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                                            file!!.extension
                                        )
                                    val refRequestBody =
                                        "api::booth.booth".toRequestBody("multipart/form-data".toMediaTypeOrNull())
                                    val refIdRequestBody = id.toString()
                                        .toRequestBody("multipart/form-data".toMediaTypeOrNull())
                                    val fieldRequestBody =
                                        "boothImg".toRequestBody("multipart/form-data".toMediaTypeOrNull())
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
//                                            navController.navigate("menu")
                                                Toast.makeText(
                                                    context,
                                                    "Berhasil menambahkan menu",
                                                    Toast.LENGTH_SHORT
                                                ).show()
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

                                    preferencesManager.saveData("boothName", boothName.value.text)
                                    preferencesManager.saveData(
                                        "boothDescription",
                                        boothDescription.value.text
                                    )
                                    preferencesManager.saveData("owner", userID)
                                    print("Successful register")
                                    navController.navigate("boothHome")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error: ${response.code()} - ${response.message()}",
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

                        override fun onFailure(call: Call<ApiResponse<BoothResponse>>, t: Throwable) {
                            print(t.message)
                        }
                    }) }
                }, modifier = Modifier
                    .width(327.dp)
                    .height(72.dp)
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