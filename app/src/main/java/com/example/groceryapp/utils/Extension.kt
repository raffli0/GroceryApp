package com.example.groceryapp.utils

import java.text.NumberFormat
import java.util.*

fun formatPrice(price: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
    return formatter.format(price)
}

