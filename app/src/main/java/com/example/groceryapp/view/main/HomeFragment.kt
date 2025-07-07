package com.example.groceryapp.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.adapter.CategoryAdapter
import com.example.groceryapp.adapter.ProductAdapter
import com.example.groceryapp.contract.ProductContract
import com.example.groceryapp.data.model.Category
import com.example.groceryapp.data.model.Product
import com.example.groceryapp.presenter.ProductPresenter
import com.example.groceryapp.utils.BottomNavVisibilityListener
import com.example.groceryapp.view.features.DetailProductFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment(), ProductContract.View {
    private lateinit var presenter: ProductPresenter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView

    private val allProducts = mutableListOf<Product>()
    private val filteredProducts = mutableListOf<Product>()

    private var bottomNavListener: BottomNavVisibilityListener? = null
    private var isNavVisible = true
    private var recyclerViewState: Parcelable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavVisibilityListener) {
            bottomNavListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        bottomNavListener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedScrollView = view.findViewById<NestedScrollView>(R.id.nestedScrollView)

        // auto hide navbar scroll start
        nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && isNavVisible) { // scroll ke bawah -> hide nav
                bottomNavListener?.hideBottomNav()
                isNavVisible = false
            } else if (scrollY < oldScrollY && !isNavVisible) { //scroll ke atas -> show nav
                bottomNavListener?.showBottomNav()
                isNavVisible = true
            }
        }
        // auto hide navbar scroll end
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val categories = listOf(
            Category("Fruits", R.drawable.ic_fruit),
            Category("Vegetables", R.drawable.ic_vegetable),
            Category("Dairy", R.drawable.ic_dairy),
            Category("Meat", R.drawable.ic_meat),
            Category("Seafood", R.drawable.ic_seafood),
            Category("Snacks", R.drawable.ic_snacks)
        )

        categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            filterProducts(selectedCategory.name)
        }

        view.findViewById<RecyclerView>(R.id.categoryLists).apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.addItemDecoration(SpaceItemDecoration(15))

        presenter = ProductPresenter(this)
        presenter.getProducts()

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onProductLoaded(data: List<Product>) {
        allProducts.clear()
        allProducts.addAll(data)
        filteredProducts.clear()
        filteredProducts.addAll(data)

        if (::productAdapter.isInitialized) {
            productAdapter.notifyDataSetChanged()
        } else {
            productAdapter = ProductAdapter(filteredProducts) { product ->
                recyclerViewState = recyclerView.layoutManager?.onSaveInstanceState()

                // Navigate ke Detail
                val detailFragment = DetailProductFragment.newInstance(product)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
            recyclerView.adapter = productAdapter
        }
//         Default filter kategori pertama supaya tampil produk kategori pertama saat load awal
//        if (categoryAdapter.itemCount > 0) {
//            val firstCategory = categoryAdapter.getCategoryAt(0).name
//            filterProducts(firstCategory)
//        }
//        recyclerView.adapter = productAdapter
    }

    override fun onResume() {
        super.onResume()
        recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterProducts(category: String) {
        filteredProducts.clear()
        filteredProducts.addAll(allProducts.filter { it.category == category })
        productAdapter.notifyDataSetChanged()
    }

    override fun onError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    //spacing antar item
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
}
