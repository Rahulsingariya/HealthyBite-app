package com.example.icecreamshop.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignupScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    // HealthyBite Gradient background
    val healthyBiteGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE8F5E9), // Light green
            Color(0xFFC8E6C9), // Fresh green
            Color(0xFFA5D6A7)  // Soft mint green
        )
    )

    // Apply gradient background to Box
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(healthyBiteGradient) // Apply gradient background
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sign Up", fontSize = 28.sp)

            Spacer(modifier = Modifier.height(16.dp))

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
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                                navController.navigate("login") // Go back to login after signup
                            } else {
                                Toast.makeText(context, "Signup Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                navController.navigate("login")
            }) {
                Text("Already have an account? Login")
            }
        }
    }
}
