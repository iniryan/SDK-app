package com.example.projectuasmobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectuasmobile.frontend.HomePage
import com.example.projectuasmobile.frontend.Login
import com.example.projectuasmobile.frontend.RolePick
import com.example.projectuasmobile.ui.theme.ProjectUASMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectUASMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val sharedPreferences: SharedPreferences =
                        LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    val navController = rememberNavController()

                    val startD: String
                    val jwt = sharedPreferences.getString("jwt", "")
                    startD = if (jwt.equals("")) {
                        "login"
                    } else {
                        "homepage"
                    }

                    NavHost(navController = navController, startDestination = startD) {
                        composable("splash") {
                            SplashScreen()
                        }
                        composable("login") {
                            Login(navController)
                        }
                        composable("homepage") {
                            HomePage(navController)
                        }
                        composable("rolepick") {
                            RolePick()
                        }

                    }
                }
            }
        }
    }
}
@Composable
fun SplashScreen() {
    val primaryColorOrg = Color(0xFFFF5F00)
    Box(
        modifier = Modifier
            .width(430.dp)
            .height(932.dp)
            .background(color = primaryColorOrg)
    ) {
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
                text = "SDK-App",
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
                onClick = {}, Modifier
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
                        color = primaryColorOrg,
                    )
                )

            }
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
                onClick = { },
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