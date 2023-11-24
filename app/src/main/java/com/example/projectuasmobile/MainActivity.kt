package com.example.projectuasmobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectuasmobile.frontend.HomePage
import com.example.projectuasmobile.frontend.Login
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

                    NavHost(navController, startDestination = startD) {
                        composable(route = "login") {
                            Login(navController)
                        }
                        composable(route = "homepage") {
                            HomePage(navController)
                        }
                    }
                }
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
