package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.contract.ProductContract
import com.example.groceryapp.model.Category

class CategoryAdapter (private val list: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val icon: ImageView = view.findViewById(R.id.categoryIcon)
        val name: TextView = view.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = list[position]

        holder.icon.setImageResource(item.iconResId)
        holder.name.text = item.name
    }


}