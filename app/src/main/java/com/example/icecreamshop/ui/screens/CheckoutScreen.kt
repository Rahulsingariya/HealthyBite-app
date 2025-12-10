package com.example.icecreamshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icecreamshop.R
import com.example.icecreamshop.ui.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }

    var selectedPayment by remember { mutableStateOf("Cash on Delivery") }

    val paymentOptions = listOf("Cash on Delivery", "QR Code")

    val healthyBiteGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE8F5E9),
            Color(0xFFC8E6C9),
            Color(0xFFA5D6A7)
        )
    )

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    fun validateInputs(): Boolean {
        val nameRegex = Regex("^[A-Za-z ]+$")
        val phoneRegex = Regex("^[0-9]{10}$")

        return when {
            name.text.isBlank() -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Name is required.")
                }
                false
            }
            !name.text.matches(nameRegex) -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Name must contain only letters.")
                }
                false
            }
            phone.text.isBlank() -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Phone number is required.")
                }
                false
            }
            !phone.text.matches(phoneRegex) -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Phone number must be 10 digits.")
                }
                false
            }
            address.text.isBlank() -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Address is required.")
                }
                false
            }
            else -> true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF66BB6A))
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Enter your details", style = MaterialTheme.typography.titleLarge, color = Color(0xFF2E7D32))
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF66BB6A),
                            unfocusedBorderColor = Color(0xFFB2DFDB)
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone Number") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF66BB6A),
                            unfocusedBorderColor = Color(0xFFB2DFDB)
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Delivery Address") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF66BB6A),
                            unfocusedBorderColor = Color(0xFFB2DFDB)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Select Payment Method", style = MaterialTheme.typography.titleMedium, color = Color(0xFF2E7D32))

                    paymentOptions.forEach { option ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (selectedPayment == option),
                                    onClick = { selectedPayment = option }
                                )
                                .padding(8.dp)
                        ) {
                            RadioButton(
                                selected = (selectedPayment == option),
                                onClick = { selectedPayment = option },
                                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF66BB6A))
                            )
                            Text(option, modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    if (selectedPayment == "QR Code") {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Scan this QR Code to pay", style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.qr_code_placeholder),
                                contentDescription = "QR Code",
                                modifier = Modifier.size(200.dp)
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        if (validateInputs()) {
                            navController.navigate(Screen.ThankYou.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A))
                ) {
                    Text("Confirm Order", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}
