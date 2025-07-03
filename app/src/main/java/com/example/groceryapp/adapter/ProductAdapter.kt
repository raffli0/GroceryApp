package com.example.groceryapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.groceryapp.R
import com.example.groceryapp.model.Product
import com.example.groceryapp.utils.formatPrice
import com.example.groceryapp.view.DetailActivity
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(private val items: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgProduct)
        val name: TextView = view.findViewById(R.id.tvName)
        val price: TextView = view.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val p = items[position]
        holder.name.text = p.name
        holder.price.text = "Rp ${formatPrice(p.price)}"
        Glide.with(holder.itemView.context).load(p.image).into(holder.img)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("PRODUCT_ID", p.id)
                putExtra("PRODUCT_NAME", p.name)
                putExtra("PRODUCT_PRICE", p.price)
                putExtra("PRODUCT_IMAGE", p.image)
                putExtra("PRODUCT_DESC", p.desc)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}
