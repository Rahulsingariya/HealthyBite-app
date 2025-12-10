package com.example.icecreamshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icecreamshop.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val cartItems by cartViewModel.cartItems.collectAsState()

    val healthyBiteGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE8F5E9), // light green
            Color(0xFFC8E6C9), // fresh green
            Color(0xFFA5D6A7)  // soft mint green
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Cart") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(healthyBiteGradient)
                .padding(16.dp)
        ) {
            if (cartItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Your cart is empty",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF2E7D32) // Dark green text
                    )
                }
            } else {
                Text(
                    text = "Items in Cart",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color(0xFF2E7D32) // Healthy green title
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    cartItems.forEach { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "₹${item.price}", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate("checkout")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A)) // Fresh green button
                ) {
                    Text("Proceed to Checkout", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}
