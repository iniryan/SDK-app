package com.example.projectuasmobile.frontend.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectuasmobile.R


@Composable
fun BoothDetail() {
    val notesField = remember {
        mutableStateOf("")
    }
    var quantity by remember { mutableIntStateOf(1) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = Color(0xFFFFFFFF))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .background(color = Color(0x1F000000)),
                painter = painterResource(id = R.drawable.thumbnail),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(9.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    //kolom untuk judul
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "Warkop Markaz", style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 36.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                color = Color(0xFF333333),
                            )
                        )
                        Text(
                            text = "Menyediakan berbagai minuman sachet, kopi, dan Rokok Batangan",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Color(0xFF9F9F9F),
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(0xFFFFFFFF))
                            .padding(bottom = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Divider(
                            modifier = Modifier
                                .border(width = 1.dp, color = Color(0xFFECECEC))
                                .padding(1.dp)
                                .height(2.dp)
                                .background(color = Color(0xFFEEEEEE))
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(390.dp)
                                    .height(62.dp)
                                    .padding(
                                        start = 16.dp, top = 18.dp, end = 10.dp, bottom = 18.dp
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(
                                    10.dp, Alignment.Start
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "Daftar Menu", style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 26.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                        color = Color(0xFF333333),
                                    )
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .width(390.dp)
                                    .height(1.dp)
                                    .background(color = Color(0xFFEEEEEE))
                            )
                            Column(
                                modifier = Modifier
                                    .width(390.dp)
                                    .height(157.5.dp)
                                    .background(color = Color(0xFFFFFFFF))
                                    .padding(start = 16.dp, top = 14.dp, end = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(
                                    16.dp, Alignment.CenterVertically
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(127.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .width(218.dp)
                                            .height(127.dp),
                                        verticalArrangement = Arrangement.spacedBy(
                                            4.dp, Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.Start,
                                    ) {
                                        Text(
                                            text = "Nasi Telor pake Telor",

                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                lineHeight = 19.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Text(
                                            text = "Nasi campur telor dengan balutan telor.",

                                            style = TextStyle(
                                                fontSize = 13.sp,
                                                lineHeight = 18.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF757575),
                                            )
                                        )
                                        Text(
                                            text = "Rp10.000", style = TextStyle(
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                                .width(218.dp)
                                                .height(28.dp),
                                            horizontalArrangement = Arrangement.spacedBy(
                                                16.dp, Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    12.dp, Alignment.CenterHorizontally
                                                ),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.minus_icon),
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.None
                                                )
                                                Text(
                                                    text = "1",

                                                    // Title 1
                                                    style = TextStyle(
                                                        fontSize = 18.sp,
                                                        lineHeight = 26.sp,
                                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                        color = Color(0xFF333333),
                                                    )
                                                )
                                                Image(
                                                    painter = painterResource(id = R.drawable.plus_icon),
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.None
                                                )
                                            }
                                        }
                                    }
                                    Image(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(100.dp),
                                        painter = painterResource(id = R.drawable.imgplaceholder),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(0.5.dp)
                                        .background(color = Color(0xFFEEEEEE))
                                )
                                Row(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(127.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .width(218.dp)
                                            .height(127.dp),
                                        verticalArrangement = Arrangement.spacedBy(
                                            4.dp, Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.Start,
                                    ) {
                                        Text(
                                            text = "Salmon with Beurre Blanc",

                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                lineHeight = 19.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Text(
                                            text = "Seared salmon served with butter sauce & seasonal vegetables.",

                                            style = TextStyle(
                                                fontSize = 13.sp,
                                                lineHeight = 18.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF757575),
                                            )
                                        )
                                        Text(
                                            text = "Rp10.000", style = TextStyle(
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                                .width(218.dp)
                                                .height(28.dp),
                                            horizontalArrangement = Arrangement.spacedBy(
                                                16.dp, Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    12.dp, Alignment.CenterHorizontally
                                                ),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                IconButton(onClick = {
                                                    if (quantity > 1) {
                                                        quantity--
                                                    }
                                                }) {
                                                    Icon(painter = painterResource(id = R.drawable.minus_icon), contentDescription = "Add")
//                                                    Image(
//                                                        painter = painterResource(id = R.drawable.minus_icon),
//                                                        contentDescription = "image description",
//                                                        contentScale = ContentScale.None
//                                                    )
                                                }
                                                Text(
                                                    "Quantity: $quantity",

//                                                    style = TextStyle(
//                                                        fontSize = 18.sp,
//                                                        lineHeight = 26.sp,
//                                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
//                                                        color = Color(0xFF333333),
//                                                    )
                                                )
                                                IconButton(onClick = {
                                                    quantity++
                                                }) {
                                                    Icon(painter = painterResource(id = R.drawable.plus_icon), contentDescription = "Add")
//                                                    Image(
//                                                        painter = painterResource(id = R.drawable.plus_icon),
//                                                        contentDescription = "image description",
//                                                        contentScale = ContentScale.None
//                                                    )
                                                }
                                            }
                                        }
                                    }
                                    Image(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(100.dp),
                                        painter = painterResource(id = R.drawable.imgplaceholder),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(0.5.dp)
                                        .background(color = Color(0xFFEEEEEE))
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .width(390.dp)
                                    .height(157.5.dp)
                                    .background(color = Color(0xFFFFFFFF))
                                    .padding(start = 16.dp, top = 14.dp, end = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(
                                    16.dp, Alignment.CenterVertically
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(127.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .width(218.dp)
                                            .height(127.dp),
                                        verticalArrangement = Arrangement.spacedBy(
                                            4.dp, Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.Start,
                                    ) {
                                        Text(
                                            text = "Nasi Telor pake Telor",

                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                lineHeight = 19.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Text(
                                            text = "Nasi campur telor dengan balutan telor.",

                                            style = TextStyle(
                                                fontSize = 13.sp,
                                                lineHeight = 18.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF757575),
                                            )
                                        )
                                        Text(
                                            text = "Rp10.000", style = TextStyle(
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                                .width(218.dp)
                                                .height(28.dp),
                                            horizontalArrangement = Arrangement.spacedBy(
                                                16.dp, Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    12.dp, Alignment.CenterHorizontally
                                                ),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.minus_icon),
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.None
                                                )
                                                Text(
                                                    text = "1",

                                                    style = TextStyle(
                                                        fontSize = 18.sp,
                                                        lineHeight = 26.sp,
                                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                        color = Color(0xFF333333),
                                                    )
                                                )
                                                Image(
                                                    painter = painterResource(id = R.drawable.plus_icon),
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.None
                                                )
                                            }
                                        }
                                    }
                                    Image(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(100.dp),
                                        painter = painterResource(id = R.drawable.imgplaceholder),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(0.5.dp)
                                        .background(color = Color(0xFFEEEEEE))
                                )
                                //image
                                Row(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(127.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .width(218.dp)
                                            .height(127.dp),
                                        verticalArrangement = Arrangement.spacedBy(
                                            4.dp, Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.Start,
                                    ) {
                                        Text(
                                            text = "Nasi Telor pake Telor",

                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                lineHeight = 19.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Text(
                                            text = "Nasi campur telor dengan balutan telor.",

                                            style = TextStyle(
                                                fontSize = 13.sp,
                                                lineHeight = 18.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF757575),
                                            )
                                        )
                                        Text(
                                            text = "Rp10.000", style = TextStyle(
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                color = Color(0xFF333333),
                                            )
                                        )
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                                .width(218.dp)
                                                .height(28.dp),
                                            horizontalArrangement = Arrangement.spacedBy(
                                                16.dp, Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    12.dp, Alignment.CenterHorizontally
                                                ),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.minus_icon),
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.None
                                                )
                                                Text(
                                                    text = "1",

                                                    style = TextStyle(
                                                        fontSize = 18.sp,
                                                        lineHeight = 26.sp,
                                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                                        color = Color(0xFF333333),
                                                    )
                                                )
                                                Image(
                                                    painter = painterResource(id = R.drawable.plus_icon),
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.None
                                                )
                                            }
                                        }
                                    }
                                    Image(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(100.dp),
                                        painter = painterResource(id = R.drawable.imgplaceholder),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .width(358.dp)
                                        .height(0.5.dp)
                                        .background(color = Color(0xFFEEEEEE))
                                )
                            }
                        }
                        //notes
//                        Column(
//                            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
//                            horizontalAlignment = Alignment.Start,
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .width(390.dp)
//                                    .height(62.dp)
//                                    .padding(
//                                        start = 16.dp, top = 18.dp, end = 10.dp, bottom = 18.dp
//                                    ),
//                                horizontalArrangement = Arrangement.spacedBy(
//                                    10.dp, Alignment.Start
//                                ),
//                                verticalAlignment = Alignment.CenterVertically,
//                            ) {
//                                Text(
//                                    text = "Catatan", style = TextStyle(
//                                        fontSize = 18.sp,
//                                        lineHeight = 26.sp,
//                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
//                                        color = Color(0xFF333333),
//                                    )
//                                )
//                            }
//                            Column(
//                                modifier = Modifier
//                                    .width(390.dp)
//                                    .background(color = Color(0xFFFFFFFF))
//                                    .padding(start = 16.dp, end = 16.dp),
//                                verticalArrangement = Arrangement.spacedBy(
//                                    16.dp, Alignment.CenterVertically
//                                ),
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                            ) {
//                                OutlinedTextField(
//                                    value = notesField.value,
//                                    onValueChange = {
//                                        notesField.value = it
//                                    },
//                                    singleLine = false,
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .height(100.dp)
//                                        .padding(2.dp)
//                                        .border(
//                                            width = 1.5.dp,
//                                            color = Color(0xFFFF5F00),
//                                            shape = RoundedCornerShape(8.dp)
//                                        ),
//                                    placeholder = { Text(text = "Contoh: pakai sambel ya...") },
//                                    maxLines = 4
//                                )
//                            }
//                        }
                    }
                }
            }
        }
    }
}