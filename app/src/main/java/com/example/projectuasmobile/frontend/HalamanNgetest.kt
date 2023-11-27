package com.example.projectuasmobile.frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projectuasmobile.R
import kotlinx.coroutines.launch


// ini page dummy buat uji coba component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKios() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->
        // Screen content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            ItemCard()

            if (showBottomSheet) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        scope.launch {
                            sheetState.hide()
                            showBottomSheet = false
                        }
                    }
                ) {
                }
            }
        }
    }
}

@Composable
fun ItemCard() {
    var quantity by remember { mutableIntStateOf(1) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Item Description")
            Spacer(modifier = Modifier.height(8.dp))

            // Image Placeholder
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Item Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                    .shadow(4.dp, shape = MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Price: $10.99")

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(onClick = {
                        if (quantity > 1) {
                            quantity--
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove")
                    }

                    Text("Quantity: $quantity")

                    IconButton(onClick = {
                        quantity++
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }

                Button(
                    onClick = {
                    },
                    modifier = Modifier.focusTarget(),
                ) {
                    Text("Add to Cart", color = Color.White)
                }
            }
        }
    }
}
