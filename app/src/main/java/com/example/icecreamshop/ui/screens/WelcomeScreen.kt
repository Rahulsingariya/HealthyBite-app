package com.example.icecreamshop.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icecreamshop.R
import com.example.icecreamshop.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }

    // Trigger fade-in animation
    LaunchedEffect(Unit) {
        delay(400)
        visible = true
    }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE0F7FA), // Light aqua
                            Color(0xFFF1F8E9)  // Soft green
                        )
                    )
                )
                .padding(padding)
        ) {
            AnimatedVisibility(visible = visible, enter = fadeIn()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(28.dp),
                        shadowElevation = 10.dp,
                        tonalElevation = 6.dp,
                        color = Color.White.copy(alpha = 0.96f),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo), // make sure logo is updated for HealthyBite
                                contentDescription = "HealthyBite Logo",
                                modifier = Modifier
                                    .size(160.dp)
                                    .padding(bottom = 20.dp)
                            )

                            Text(
                                text = "Welcome to",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF2E7D32) // Green tone
                            )

                            Text(
                                text = "HealthyBite",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00796B), // Dark teal
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Your destination for healthy, delicious meals 🥗",
                                fontSize = 16.sp,
                                color = Color(0xFF4E342E),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )

                            Spacer(modifier = Modifier.height(28.dp))

                            Button(
                                onClick = { navController.navigate(Screen.Home.route) },
                                shape = RoundedCornerShape(24.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF00796B),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                            ) {
                                Text(
                                    text = "Enter Shop",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
