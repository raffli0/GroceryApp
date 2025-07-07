package com.example.groceryapp.view.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.groceryapp.adapter.ProductAdapter
import com.example.groceryapp.contract.ProductContract
import com.example.groceryapp.data.model.Product
import com.example.groceryapp.databinding.FragmentCategoryProductBinding
import com.example.groceryapp.presenter.ProductPresenter
import com.example.groceryapp.view.main.HomeFragment


class CategoryProductFragment : Fragment(), ProductContract.View {

    private var _binding: FragmentCategoryProductBinding? = null
    private val binding get() = _binding!!


    private lateinit var presenter: ProductContract.Presenter
    private lateinit var adapter: ProductAdapter
    private lateinit var categoryName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryName = arguments?.getString("categoryName") ?: ""
        presenter = ProductPresenter(this, )
    }

    override fun onProductLoaded(data: List<Product>) {
        TODO("Not yet implemented")
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

}