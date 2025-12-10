package com.example.icecreamshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.icecreamshop.ui.screens.*
import com.example.icecreamshop.viewmodel.CartViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    // Shared ViewModel for cart state
    val cartViewModel = remember { CartViewModel() }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // your screens

    composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Signup.route) {
            SignupScreen(navController)
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController, cartViewModel)
        }

        composable(Screen.Cart.route) {
            CartScreen(navController, cartViewModel)
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(navController)
        }
        composable(Screen.ThankYou.route) {
            ThankYouScreen(navController)
        }


    }
}







