package com.example.groceryapp

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.adapter.ProductAdapter
import com.example.groceryapp.contract.ProductContract
import com.example.groceryapp.model.Product
import com.example.groceryapp.presenter.ProductPresenter

class MainActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var presenter: ProductPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(
            SpaceItemDecoration(16)
        )

        presenter = ProductPresenter(this)
        presenter.getProducts()
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
        progressBar.visibility = View.GONE
        recyclerView.adapter = ProductAdapter(data)
    }

    override fun onError(msg: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
