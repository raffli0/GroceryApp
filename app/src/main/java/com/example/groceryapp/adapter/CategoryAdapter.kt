package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.Category

class CategoryAdapter (
    private val categories: List<Category>,
    private val onCategoryClick: (Category)-> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val icon: ImageView = view.findViewById(R.id.categoryIcon)
        val name: TextView = view.findViewById(R.id.categoryName)

        fun bind(category: Category) {
            name.text = category.name
            icon.setImageResource(category.iconResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)

        holder.itemView.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                // Update posisi terpilih
                val previousPosition = selectedPosition
                selectedPosition = pos

                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                // Trigger callback
                onCategoryClick(categories[pos])
            }
        }
    }

    fun getCategoryAt(position: Int): Category {
        return categories[position]
    }
}