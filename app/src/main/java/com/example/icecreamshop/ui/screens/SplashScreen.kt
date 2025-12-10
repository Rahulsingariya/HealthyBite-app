package com.example.icecreamshop.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    var showText by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = { OvershootInterpolator(3f).getInterpolation(it) }
            )
        )
        delay(300L)
        showText = true
        delay(1800L)
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9)), // soft healthy green
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Make sure your HealthyBite logo is here
                contentDescription = "HealthyBite Logo",
                modifier = Modifier
                    .size(180.dp)
                    .scale(scale.value)
            )

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedVisibility(visible = showText, enter = fadeIn(tween(800))) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "HealthyBite",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32), // Healthy green tone
                        letterSpacing = 1.5.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Fuel your body with every bite 🥗",
                        fontSize = 16.sp,
                        color = Color(0xFF4E342E),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
