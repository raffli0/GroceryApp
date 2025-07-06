package com.example.groceryapp

import android.graphics.Rect
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.adapter.CategoryAdapter
import com.example.groceryapp.adapter.ProductAdapter
import com.example.groceryapp.contract.ProductContract
import com.example.groceryapp.model.Category
import com.example.groceryapp.model.Product
import com.example.groceryapp.presenter.ProductPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var presenter: ProductPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categories = listOf(
            Category("Fruit", R.drawable.ic_fruit),
            Category("Vegetables", R.drawable.ic_vegetable),
            Category("Dairy", R.drawable.ic_dairy),
            Category("Meat", R.drawable.ic_meat),
            Category("Seafood", R.drawable.ic_seafood),
            Category("Snacks", R.drawable.ic_snacks)
        )

        val categoryAdapter = CategoryAdapter(categories)

        findViewById<RecyclerView>(R.id.categoryLists).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        recyclerView = findViewById(R.id.recyclerView)
//        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(
            SpaceItemDecoration(15)
        )

        presenter = ProductPresenter(this)
        presenter.getProducts()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var isNavVisible = true

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // TODO: Load Home Fragment or Activity
                    true
                }

                R.id.nav_explore -> {
                    // TODO: Load Explore
                    true
                }

                R.id.nav_cart -> {
                    // TODO: Load Cart
                    true
                }

                R.id.nav_profile -> {
                    // TODO: Load Profile
                    true
                }

                else -> false
            }
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && isNavVisible) {
                    bottomNav.animate().translationY(bottomNav.height.toFloat()).duration = 200
                    isNavVisible = false
                } else if (dy < 0 && !isNavVisible) {
                    bottomNav.animate().translationY(0f).duration = 200
                    isNavVisible = true
                }
            }
        })
    }

    //spacing antar item gak dempet
    class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.left = space / 2
            outRect.right = space / 2
            outRect.top = space / 2
            outRect.bottom = space / 2
        }
    }


    override fun onProductLoaded(data: List<Product>) {
        recyclerView.adapter = ProductAdapter(data)
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }



}
