package com.example.groceryapp.data.model

import java.io.Serializable

class Product (
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val unit: String = "",
    val price: Int = 0,
    val image: String = "",
    val desc: String = ""
) : Serializable
