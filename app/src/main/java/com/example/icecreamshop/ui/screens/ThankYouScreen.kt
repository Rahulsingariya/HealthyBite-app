package com.example.icecreamshop.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icecreamshop.ui.navigation.Screen

@Composable
fun ThankYouScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }

    // Trigger animation after composition
    LaunchedEffect(Unit) {
        visible = true
    }

    // HealthyBite Gradient background
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE8F5E9), // Light green
            Color(0xFFC8E6C9), // Fresh green
            Color(0xFFA5D6A7)  // Soft mint green
        )
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground) // Gradient background
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = scaleIn(animationSpec = tween(600))
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Success",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(120.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Thank You for Your Order!",
                    fontSize = 26.sp,
                    fontFamily = FontFamily.SansSerif, // Default sans-serif system font
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF388E3C), // Green button
                        contentColor = Color.White
                    )
                ) {
                    Text("Back to Home", fontSize = 16.sp, fontFamily = FontFamily.SansSerif) // Default sans-serif font
                }
            }
        }
    }
}
