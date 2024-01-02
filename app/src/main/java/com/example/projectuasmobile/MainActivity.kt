package com.example.projectuasmobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectuasmobile.data.OrderDetailsDataWrapper
import com.example.projectuasmobile.frontend.auth.Login
import com.example.projectuasmobile.frontend.auth.OnboardingScreen
import com.example.projectuasmobile.frontend.auth.Register
import com.example.projectuasmobile.frontend.auth.RegisterBooth
import com.example.projectuasmobile.frontend.auth.RolePick
import com.example.projectuasmobile.frontend.booth.AddMenu
import com.example.projectuasmobile.frontend.booth.BoothHomePage
import com.example.projectuasmobile.frontend.booth.BoothProfile
import com.example.projectuasmobile.frontend.booth.DetailTransaction
import com.example.projectuasmobile.frontend.booth.EditMenu
import com.example.projectuasmobile.frontend.booth.EditProfile
import com.example.projectuasmobile.frontend.booth.MenuList
import com.example.projectuasmobile.frontend.booth.TransactionPage
import com.example.projectuasmobile.frontend.customer.BoothDetail
import com.example.projectuasmobile.frontend.customer.CheckOutPage
import com.example.projectuasmobile.frontend.customer.CustomerTransaction
import com.example.projectuasmobile.frontend.customer.HomePage
import com.example.projectuasmobile.frontend.customer.PaymentPage
import com.example.projectuasmobile.ui.theme.ProjectUASMobileTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectUASMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val sharedPreferences: SharedPreferences =
                        LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    val navController = rememberNavController()
                    val jwt = sharedPreferences.getString("jwt", "")

                    val startD: String = if (jwt.equals("")) {
                        "onboarding"
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
                        composable("detailBooth/{boothID}/{boothName}/{boothDescription}") { backStackEntry ->
                            BoothDetail(
                                navController,
                                backStackEntry.arguments?.getString("boothID"),
                                backStackEntry.arguments?.getString("boothName"),
                                backStackEntry.arguments?.getString("boothDescription"),
                            )
                        }
                        composable("editMenu/{foodID}/{foodName}/{foodDescription}/{foodPrice}/{newUrl}") { backStackEntry ->
                            EditMenu(
                                navController,
                                backStackEntry.arguments?.getString("foodID"),
                                backStackEntry.arguments?.getString("foodName"),
                                backStackEntry.arguments?.getString("foodDescription"),
                                backStackEntry.arguments?.getString("foodPrice"),
                                backStackEntry.arguments?.getString("newUrl"),
                            )
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
                        composable("boothprofile") {
                            BoothProfile(navController)
                        }
                        composable("editProfile/{boothID}/{boothName}/{boothDesc}/{open}/{newUrl}") { backStackEntry ->
                            EditProfile(
                                navController,
                                backStackEntry.arguments?.getString("boothID"),
                                backStackEntry.arguments?.getString("boothName"),
                                backStackEntry.arguments?.getString("boothDesc"),
                                backStackEntry.arguments?.getString("open"),
                                backStackEntry.arguments?.getString("newUrl"),
                            )
                        }
                        composable("checkout/{orderList}") {
                            val orderList = it.arguments?.getString("orderList")
                            println(orderList)
                            val listReplace = orderList?.replace("::uploads::", "/uploads/")
                            val objectArrayParameter = Gson().fromJson(
                                listReplace,
                                object : TypeToken<List<OrderDetailsDataWrapper>>() {}.type
                            ) as List<OrderDetailsDataWrapper>

                            CheckOutPage(objectArrayParameter, navController)
                        }
                        composable("payment") {
                            PaymentPage(navController)
                        }
                        composable("detailTransaction") {
                            DetailTransaction(navController)
                        }
                        composable("customerTransaction") {
                            CustomerTransaction(navController)
                        }
                        composable("transaction") {
                            TransactionPage(navController)
                        }
                    }
                }
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
                label = "Transaksi", iconResId = R.drawable.transaksi, destination = "customerTransaction"
            ),
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.destination,
                onClick = {
                    navController.navigate(it.destination)
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
                label = "Transaksi", iconResId = R.drawable.transaksi, destination = "transaction"
            ),
            BottomNavItem(
                label = "Profil", iconResId = R.drawable.profilee, destination = "boothprofile"
            ),
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.destination,
                onClick = {
                    navController.navigate(it.destination)
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
