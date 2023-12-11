package com.example.projectuasmobile.frontend.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.RegisterData
import com.example.projectuasmobile.response.AuthResponse
import com.example.projectuasmobile.service.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun Register(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    val primaryColorOrg = Color(0xFFFF5F00)
    val usernameField = remember { mutableStateOf(TextFieldValue("")) }
    val fullnameField = remember { mutableStateOf(TextFieldValue("")) }
    val emailField = remember { mutableStateOf(TextFieldValue("")) }
    val passwordField = remember { mutableStateOf(TextFieldValue("")) }
    val passwordVisible = remember { mutableStateOf(false) }

    val baseUrl = "http://10.0.2.2:1337/api/"
    //KALAU TIDAK DI EMULATOR
    //val baseUrl = "http://10.217.17.11:1337/api/"

    var jwt by remember { mutableStateOf("") }
    jwt = preferencesManager.getData("jwt")

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
                text = "Daftar Akun Dulu Untuk Mulai Jualan",
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
            OutlinedTextField(value = fullnameField.value,
                onValueChange = {
                    fullnameField.value = it
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
                        text = "Fullname",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                })
            Spacer(modifier = Modifier.height(28.dp))
            OutlinedTextField(value = usernameField.value,
                onValueChange = {
                    usernameField.value = it
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
                        text = "Username",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                })
            Spacer(modifier = Modifier.height(28.dp))
            OutlinedTextField(value = emailField.value,
                onValueChange = {
                    emailField.value = it
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
                        text = "Email",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                })
            Spacer(modifier = Modifier.height(28.dp))
            OutlinedTextField(value = passwordField.value,
                onValueChange = {
                    passwordField.value = it
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
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                },
                placeholder = {
                    Text(
                        text = "Password",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF5F00),
                        )
                    )
                },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible.value = !passwordVisible.value },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Icon(
                            painter = if (passwordVisible.value) painterResource(id = R.drawable.eyeopened)
                            else painterResource(id = R.drawable.eyeclosed),
                            contentDescription = "Toggle Password",
                            tint = primaryColorOrg,
                        )
                    }
                })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val retrofit =
                        Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(AuthService::class.java)
                    val call = retrofit.saveData(
                        RegisterData(
                            fullname = fullnameField.value.text,
                            email = emailField.value.text,
                            username = usernameField.value.text,
                            password = passwordField.value.text
                        )
                    )
                    call.enqueue(object : Callback<AuthResponse> {
                        override fun onResponse(
                            call: Call<AuthResponse>,
                            response: Response<AuthResponse>
                        ) {
                            print(response.code())
                            if (response.code() == 200) {
                                jwt = response.body()?.jwt!!
                                val respUser = response.body()?.user!!
                                val userID = respUser.id.toString()
                                preferencesManager.saveData("jwt", jwt)
                                preferencesManager.saveData("userID", userID)
                                preferencesManager.saveData("fullname", fullnameField.value.text)
                                preferencesManager.saveData("username", usernameField.value.text)
                                preferencesManager.saveData("email", emailField.value.text)
                                preferencesManager.saveData("password", passwordField.value.text)
                                print("Successful register")
                                navController.navigate("registerBooth")
                            } else if (response.code() == 400) {
                                print("bad request 400")
                                Toast.makeText(
                                    context,
                                    "Username atau password salah",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
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

                    text = "DAFTAR",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(600),
                        color = Color.White,
                    )
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Row {
                Text(
                    text = "Sudah punya akun?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Left
                    ),
                    modifier = Modifier.padding(top = 28.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                ClickableText(
                    text = AnnotatedString("Masuk aja"),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color(0xFF6650a4),
                        textAlign = TextAlign.Left
                    ),
                    modifier = Modifier.padding(top = 28.dp)
                ) {
                    navController.navigate("login")
                }
            }
        }
    }
}