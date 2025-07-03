package com.example.groceryapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.groceryapp.R
import com.example.groceryapp.model.Product
import com.example.groceryapp.utils.formatPrice
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var name: TextView
    private lateinit var price: TextView
    private lateinit var desc: TextView
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        img = findViewById(R.id.imgDetail)
        name = findViewById(R.id.tvDetailName)
        price = findViewById(R.id.tvDetailPrice)
        desc = findViewById(R.id.tvDetailDesc)
        btnAdd = findViewById(R.id.btnDetailAdd)

        // Ambil data dari Intent
        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productPrice = intent.getIntExtra("PRODUCT_PRICE", 0)
        val productImage = intent.getStringExtra("PRODUCT_IMAGE")
        val productDesc = intent.getStringExtra("PRODUCT_DESC")

        // Set data ke view
        name.text = productName
        price.text = "Rp ${formatPrice(productPrice)}"
        desc.text = productDesc
        Glide.with(this).load(productImage).into(img)



//        btnAdd.setOnClickListener {
//            val product = Product(
//                id = intent.getStringExtra("PRODUCT_ID") ?: "",
//                name = productName ?: "",
//                price = productPrice,
//                image = productImage ?: "",
//                desc = productDesc ?: ""
//            )
//            CartManager.addToCart(this, product)
//            Toast.makeText(this, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
//        }
    }
}
