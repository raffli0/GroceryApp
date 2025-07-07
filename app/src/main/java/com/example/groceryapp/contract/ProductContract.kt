package com.example.groceryapp.contract
import com.example.groceryapp.data.model.Product

interface ProductContract {
    interface View {
        fun onProductLoaded(data: List<Product>)
        fun onError(msg: String)
    }

    interface Presenter {
        fun getProducts()
    }
}
