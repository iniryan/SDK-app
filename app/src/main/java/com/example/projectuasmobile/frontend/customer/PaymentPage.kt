package com.example.projectuasmobile.frontend.customer

import android.content.Context
import android.widget.HorizontalScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectuasmobile.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun PaymentPage(navController: NavController, context: Context = LocalContext.current){
Surface(modifier = Modifier
    .fillMaxSize()
    .padding(12.dp)) {

    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Pilih Pembayaran",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontWeight = FontWeight(600),
                color = Color(0xCCFF5F00),
                textAlign = TextAlign.Center,
            )
        )

        Text(
            text = " Metode pembayaran ",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF1E1E1E),
                textAlign = TextAlign.Center,
            )
        )
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment =
        Alignment.CenterHorizontally){

        Image(
            modifier = Modifier.width(362.dp)
                .height(222.dp)
                .clickable { navController.navigate("") },
            painter = painterResource(id = R.drawable.tunai),
            contentDescription = "image description",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.padding(top =20.dp))
        Image(
            modifier = Modifier.width(362.dp)
                .height(222.dp)
                .clickable { navController.navigate("") },
            painter = painterResource(id = R.drawable.cashless),
            contentDescription = "image description",
            contentScale = ContentScale.Fit,
            )

        }

    }
}
}