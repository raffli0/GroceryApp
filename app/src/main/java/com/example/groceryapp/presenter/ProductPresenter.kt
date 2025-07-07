package com.example.groceryapp.presenter

import com.example.groceryapp.contract.ProductContract
import com.example.groceryapp.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class ProductPresenter(private val view: ProductContract.View) : ProductContract.Presenter {

    private val db = FirebaseFirestore.getInstance()

    // ambil data products dari firebase
    override fun getProducts() {
        db.collection("products")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    view.onError(error.message ?: "Unknown error")
                    return@addSnapshotListener
                }
                if (snapshot != null && !snapshot.isEmpty) {
                    val list = snapshot.documents.mapNotNull { it.toObject(Product::class.java) }
                    view.
                    onProductLoaded(list)
                }
            }
    }
}
