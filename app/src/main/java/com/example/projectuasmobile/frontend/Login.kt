package com.example.projectuasmobile.frontend

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.LoginService
import com.example.projectuasmobile.PreferencesManager
import com.example.projectuasmobile.R
import com.example.projectuasmobile.data.LoginData
import com.example.projectuasmobile.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }

    val usernameField = remember { mutableStateOf(TextFieldValue("")) }
    val passwordField = remember { mutableStateOf(TextFieldValue("")) }
    val passwordVisible = remember { mutableStateOf(false) }

    val baseUrl = "http://10.0.2.2:1337/api/"
    //KALAU TIDAK DI EMULATOR
    //val baseUrl = "http://10.217.17.11:1337/api/"

    var jwt by remember { mutableStateOf("") }
    jwt = preferencesManager.getData("jwt")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "SDK-app", style = TextStyle(
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    textAlign = TextAlign.Left
                ), modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Memudahkan untuk order anda", style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Left
                ), modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Email", style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 48.dp)
            )
            OutlinedTextField(value = usernameField.value,
                onValueChange = {
                    usernameField.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00), shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Contoh: example@test.com") })
            Text(
                text = "Password", style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 28.dp)
            )
            OutlinedTextField(value = passwordField.value,
                onValueChange = {
                    passwordField.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(
                        width = 1.5.dp, color = Color(0xFFFF5F00), shape = RoundedCornerShape(8.dp)
                    ),
                placeholder = { Text(text = "Masukkan password") },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible.value = !passwordVisible.value },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Icon(
                            painter = if (passwordVisible.value) painterResource(id = R.drawable.eye_open)
                            else painterResource(id = R.drawable.eye_close),
                            contentDescription = "Toggle Password"
                        )
                    }
                })
            Spacer(modifier = Modifier.padding(20.dp))
            ElevatedButton(modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
                .padding(2.dp)
                .height(48.dp), colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
            ), shape = RoundedCornerShape(8.dp), onClick = {
                val retrofit =
                    Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(LoginService::class.java)
                val call = retrofit.getData(
                    LoginData(
                        usernameField.value.text,
                        passwordField.value.text
                    )
                )
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        print(response.code())
                        if (response.code() == 200) {
                            jwt = response.body()?.jwt!!
                            preferencesManager.saveData("jwt", jwt)
                            navController.navigate("homepage")
                        } else if (response.code() == 400) {
                            print("bad request 400")
                            Toast.makeText(
                                context,
                                "Username atau password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        print(t.message)
                    }
                })
            }) {
                Text(
                    text = "Mulai Berbagi", style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Text(
                text = "atau masuk dengan",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 14.dp)
                    .padding(bottom = 28.dp)
            )
            Row {
                Text(
                    text = "Belum punya akun?", style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Left
                    ), modifier = Modifier.padding(top = 48.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                ClickableText(
                    text = AnnotatedString("Daftar dulu"), style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color(0xFF6650a4),
                        textAlign = TextAlign.Left
                    ), modifier = Modifier.padding(top = 48.dp)
                ) {
                    //navController.navigate("register")
                }
            }

        }
    }
}
