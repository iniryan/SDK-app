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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectuasmobile.frontend.auth.Login
import com.example.projectuasmobile.frontend.auth.Register
import com.example.projectuasmobile.frontend.auth.RegisterBooth
import com.example.projectuasmobile.frontend.auth.RolePick
import com.example.projectuasmobile.frontend.booth.AddMenu
import com.example.projectuasmobile.frontend.booth.BoothHomePage
import com.example.projectuasmobile.frontend.booth.BoothProfile
import com.example.projectuasmobile.frontend.booth.EditProfile
import com.example.projectuasmobile.frontend.booth.MenuList
import com.example.projectuasmobile.frontend.customer.BoothDetail
import com.example.projectuasmobile.frontend.customer.CheckOutPage
import com.example.projectuasmobile.frontend.customer.HomePage
import com.example.projectuasmobile.frontend.customer.PaymentPage
import com.example.projectuasmobile.frontend.customer.com.example.projectuasmobile.frontend.customer.Kios
import com.example.projectuasmobile.ui.theme.ProjectUASMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectUASMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    val navController = rememberNavController()
                    val jwt = sharedPreferences.getString("jwt", "")

                    val startD: String = if (jwt.equals("")) {
                        "detailBooth"
//                        "onboarding"
                    } else {
                        "boothHome"

                    }

                    NavHost(navController = navController, startDestination = startD) {
                        composable("onboarding") {
                            OnboardingScreen(navController)
                        }
                        composable("login") {
                            Login(navController)
                        }
                        composable("homepage") {
                            HomePage(navController)
                        }
                        composable("rolepick") {
                            RolePick(navController)
                        }
                        composable("detailBooth") {
                            BoothDetail(navController)
                        }
                        composable("boothHome") {
                            BoothHomePage(navController)
                        }
                        composable("register") {
                            Register(navController)
                        }
                        composable("registerBooth") {
                            RegisterBooth(navController)
                        }
                        composable("menu") {
                            MenuList(navController)
                        }
                        composable("addmenu") {
                            AddMenu(navController)
                        }
                        composable("kios") {
                            Kios(navController)
                        }
                        composable("boothprofile") {
                            BoothProfile(navController)
                        }
                        composable("editProfile") {
                            EditProfile(navController)
                        }
                        composable("checkout") {
                            CheckOutPage(navController)
                        }
                        composable("payment") {
                            PaymentPage(navController)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun OnboardingScreen(navController: NavController) {
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
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally){
            Button(
                onClick = {
                    navController.navigate("rolepick")
                }, Modifier
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
fun BottomNavCustomer(navController: NavController) {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Beranda", iconResId = R.drawable.home, destination = "homepage"
            ),
            BottomNavItem(
                label = "Kios", iconResId = R.drawable.booth, destination = "kios"
            ),
            BottomNavItem(
                label = "Transaksi", iconResId = R.drawable.transaksi, destination = "transaksi"
            ),
            BottomNavItem(
                label = "Profil", iconResId = R.drawable.keluar, destination = "profil"
            ),
        )
        bottomNavigation.map {
            NavigationBarItem(

                selected = it.label == bottomNavigation[0].label,
                onClick = {
                    navController.navigate(it.label.lowercase())
                },
                icon = {
                    Image(
                        painter = painterResource(id = it.iconResId),
                        contentDescription = it.label,
                        modifier = Modifier.size(24.dp),
                    )
                },
                label = { Text(text = it.label, color = Color(0xFFFF5F00)) },
            )
        }
    }
}
@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Beranda", iconResId = R.drawable.home, destination = "boothHome"
            ),
            BottomNavItem(
                label = "Menu", iconResId = R.drawable.booth, destination = "menu"
            ),
            BottomNavItem(
                label = "Transaksi", iconResId = R.drawable.transaksi, destination = "transaksi"
            ),
            BottomNavItem(
                label = "Profil", iconResId = R.drawable.keluar, destination = "profil"
            ),
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = it.label == bottomNavigation[0].label,
                onClick = {
                    navController.navigate(it.label.lowercase())
                },
                icon = {
                    Image(
                        painter = painterResource(id = it.iconResId),
                        contentDescription = it.label,
                        modifier = Modifier.size(24.dp),
                    )
                },
                label = { Text(text = it.label, color = Color(0xFFFF5F00)) },
            )
        }
    }
}

data class BottomNavItem(val label: String, val iconResId: Int, val destination: String)
