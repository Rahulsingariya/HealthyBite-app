package com.example.icecreamshop.viewmodel

import androidx.lifecycle.ViewModel
import com.example.icecreamshop.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun addToCart(item: CartItem) {
        if (_cartItems.value.none { it.name == item.name }) {
            _cartItems.value = _cartItems.value + item
        }
    }

    fun removeFromCart(item: CartItem) {
        _cartItems.value = _cartItems.value.filterNot { it.name == item.name }
    }
}


