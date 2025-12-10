package com.example.icecreamshop.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Cart : Screen("cart")      // ✅ Used in navigation
    object Checkout : Screen("checkout")
    object ThankYou : Screen("thank_you")
}
