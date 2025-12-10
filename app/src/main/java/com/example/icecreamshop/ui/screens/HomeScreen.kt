package com.example.icecreamshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icecreamshop.R
import com.example.icecreamshop.model.CartItem
import com.example.icecreamshop.ui.navigation.Screen
import com.example.icecreamshop.viewmodel.CartViewModel

@Composable
fun HomeScreen(navController: NavController, cartViewModel: CartViewModel) {
    val items = listOf(
        CartItem("Green Detox Smoothie", 89.0, R.drawable.smoothie_green),
        CartItem("Quinoa Salad", 120.0, R.drawable.quinoa_salad),
        CartItem("Avocado Toast", 99.0, R.drawable.avocado_toast),
        CartItem("Mixed Fruit Bowl", 85.0, R.drawable.fruit_bowl),
        CartItem("Greek Yogurt Parfait", 110.0, R.drawable.yogurt_parfait),
        CartItem("Chickpea Wrap", 130.0, R.drawable.chickpea_wrap),
        CartItem("Berry Smoothie", 95.0, R.drawable.smoothie_berry),
        CartItem("Grilled Veggie Platter", 145.0, R.drawable.grilled_veggies),
        CartItem("Oats & Banana Bowl", 90.0, R.drawable.oats_banana),
        CartItem("Hummus & Veggie Sticks", 80.0, R.drawable.hummus_veggies)
    )

    val selectedItems = remember { mutableStateMapOf<String, Boolean>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFE8F5E9), Color(0xFFFFFDE7))
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = "🥗 Choose Your Healthy Bite!",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF2E7D32)
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                val isSelected = selectedItems[item.name] ?: false

                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 12.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF1B5E20)
                            )
                            Text(
                                text = "₹${item.price}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Button(
                            onClick = {
                                selectedItems[item.name] = !isSelected
                                if (!isSelected) cartViewModel.addToCart(item)
                                else cartViewModel.removeFromCart(item)
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) Color.Gray else Color(0xFF66BB6A),
                                contentColor = Color.White
                            )
                        ) {
                            Text(if (isSelected) "Remove" else "Add")
                        }
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate(Screen.Cart.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF388E3C),
                contentColor = Color.White
            )
        ) {
            Text("🛒 Go to Cart", fontSize = 16.sp)
        }
    }
}
