package com.example.icecreamshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.icecreamshop.model.CartItem

@Composable
fun ItemCard(
    item: CartItem,
    isSelected: Boolean,
    onItemSelected: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemSelected(!isSelected) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE1F5FE) else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(item.name, style = MaterialTheme.typography.titleMedium)
                Text("$${item.price}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


