package com.example.icecreamshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icecreamshop.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val auth = FirebaseAuth.getInstance()


    val healthyBiteGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE8F5E9),
            Color(0xFFC8E6C9),
            Color(0xFFA5D6A7)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Login") })
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(healthyBiteGradient)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // ✅ Navigate to Welcome screen after successful login
                                    navController.navigate(Screen.Welcome.route)
                                } else {
                                    errorMessage = task.exception?.message
                                }
                            }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = {
                    navController.navigate(Screen.Signup.route)
                }) {
                    Text("Don't have an account? Sign up")
                }

                errorMessage?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
