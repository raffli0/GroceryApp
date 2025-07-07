package com.example.groceryapp.view.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.groceryapp.databinding.ProductDetailFragment
import com.example.groceryapp.data.model.Product

class ProductDetailFragment : Fragment() {

    private var _binding: ProductDetailFragment? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_PRODUCT = "arg_product"

        fun newInstance(product: Product): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARG_PRODUCT, product)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductDetailFragment.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.getSerializable(ARG_PRODUCT) as? Product
        product?.let {
            binding.tvDetailName.text = it.name
            binding.tvDetailPrice.text = "Rp ${it.price}"
            binding.tvDetailDesc.text = it.desc
            Glide.with(requireContext()).load(it.image).into(binding.imgDetail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
