package com.example.projectuasmobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
<<<<<<< HEAD
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
=======
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
>>>>>>> baaee63cd4fc033c3c9471a313cc5e155da5412d
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
<<<<<<< HEAD
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectuasmobile.frontend.HomePage
import com.example.projectuasmobile.frontend.Login
=======
import androidx.compose.ui.layout.ContentScale
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
>>>>>>> baaee63cd4fc033c3c9471a313cc5e155da5412d
import com.example.projectuasmobile.ui.theme.ProjectUASMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectUASMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
<<<<<<< HEAD
                    val sharedPreferences: SharedPreferences =
                        LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    val navController = rememberNavController()
=======
                    SplashScreen()
//                    Login()
                }
            }
        }
    }
}
@Composable
fun SplashScreen() {
    val Primary = Color(0xFFFF5F00)
    Box(
        modifier = Modifier
            .width(430.dp)
            .height(932.dp)
            .background(color = Color(0xFFFF5F00))
    ) {
//        Image(
//            modifier =Modifier
//                .width(33.5444.dp)
//                .height(49.dp),
//            painter = painterResource(id = R.drawable.logokecil),
//            contentDescription = "logo",
//            contentScale = ContentScale.FillBounds
//        )

        Row (modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)) {
            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(32.dp),
                painter = painterResource(id = R.drawable.logokecil),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Text(
                modifier = Modifier
                    .width(104.dp)
                    .height(30.dp),
                text = "SDK-Apps",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFEEEEEE),
                )
            )

        }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.splashtext),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds
                )
            }
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally){
            Button(
                onClick = { /*TODO*/ }, Modifier
                    .width(327.dp)
                    .height(72.dp)
                    .padding(start = 10.dp, top = 12.dp, end = 10.dp, bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Mulai Gunakan Aplikasi",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFF5F00),
                    )
                )

            }
        }

    }
}
>>>>>>> baaee63cd4fc033c3c9471a313cc5e155da5412d

                    val startD: String
                    val jwt = sharedPreferences.getString("jwt", "")
                    startD = if (jwt.equals("")) {
                        "login"
                    } else {
                        "homepage"
                    }

<<<<<<< HEAD
                    NavHost(navController, startDestination = startD) {
                        composable(route = "login") {
                            Login(navController)
                        }
                        composable(route = "homepage") {
                            HomePage(navController)
                        }
=======
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "SDK-app",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    textAlign = TextAlign.Left
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Memudahkan untuk order anda",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Left
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Email",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 48.dp)
            )
            OutlinedTextField(
                value = usernameField.value,
                onValueChange = {
                    usernameField.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp),
                placeholder = { Text(text = "Contoh: example@test.com") }
            )
            Text(
                text = "Password",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF1E1E1E),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 28.dp)
            )
            OutlinedTextField(
                value = passwordField.value,
                onValueChange = {
                    passwordField.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(2.dp),
                placeholder = { Text(text = "Masukkan password") },
                visualTransformation =
                if (passwordVisible.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible.value = !passwordVisible.value },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Icon(
                            painter =
                            if (passwordVisible.value)
                                painterResource(id = R.drawable.eye_open)
                            else
                                painterResource(id = R.drawable.eye_close),
                            contentDescription = "Toggle Password"
                        )
>>>>>>> baaee63cd4fc033c3c9471a313cc5e155da5412d
                    }
                }
            }
<<<<<<< HEAD
=======
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

>>>>>>> baaee63cd4fc033c3c9471a313cc5e155da5412d
        }
    }
}

@Composable
fun BottomNavigation() {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Beranda", icon = Icons.Default.Home
            ),
            BottomNavItem(
                label = "Kios", icon = Icons.Default.Star
            ),
            BottomNavItem(
                label = "Transaksi", icon = Icons.Default.ShoppingCart
            ),
            BottomNavItem(
                label = "Keluar", icon = Icons.Default.ExitToApp
            ),
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = it.label == bottomNavigation[0].label,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.label,
                        tint = Color(0xFFFF5F00)
                    )
                },
                label = { Text(text = it.label, color = Color(0xFFFF5F00)) },
            )
        }
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector)
