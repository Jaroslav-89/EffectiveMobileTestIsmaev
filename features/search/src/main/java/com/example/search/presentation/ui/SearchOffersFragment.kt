package com.example.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.search.databinding.FragmentSearchOffersBinding

class SearchOffersFragment : Fragment() {

    private var _binding: FragmentSearchOffersBinding? = null
    private val binding get() = _binding!!

//    private val adapter = ProductAdapter { productId ->
//        findNavController().navigate(
//            R.id.action_productsFragment_to_productDetailsFragment,
//            ProductDetailsFragment.createArgs(productId)
//        )
//    }

//    private val adapter = ProductAdapter { productId ->
//        findNavController().navigate(
//            R.id.action_productsFragment_to_productDetailsFragment,
//            ProductDetailsFragment.createArgs(productId)
//        )
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchOffersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    private fun initRecyclerView() {
//        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
//        binding.rvProducts.adapter = adapter
//    }

    private fun observeViewModel() {

    }

//    private fun renderState(state: ProductDetailsScreenState) {
//        when (state) {
//            is ProductDetailsScreenState.Loading -> showLoading()
//            is ProductDetailsScreenState.Content -> showContent(state.product)
//            is ProductDetailsScreenState.Error -> showError(state.error)
//        }
//    }

//    private fun showContent(product: Product) {
//        hideViews(progressBarGone = true, errorLayoutGone = true)
//
//        with(binding) {
//            Glide.with(ivProductPhoto)
//                .load(product.thumbnailUrl)
//                .placeholder(R.drawable.product_image_placeholder)
//                .transform(
//                    CenterCrop(),
//                    RoundedCorners(
//                        resources.getDimensionPixelSize(R.dimen.thumbnail_image_corner_radius)
//                    ),
//                )
//                .into(ivProductPhoto)
//            tvName.text = product.name
//            tvDescription.text = product.description.trim()
//            tvBrand.text = product.brand.trim()
//            tvPrice.text = "${product.price}$"
//            tvCategory.text = product.category
//            tvRating.text = product.rating.toString()
//        }
//    }
//
//    private fun showError(error: ErrorType) {
//        hideViews(progressBarGone = true, contentLayoutGone = true)
//        binding.tvError.text = getTextError(error)
//    }
//
//    private fun showLoading() {
//        hideViews(errorLayoutGone = true, contentLayoutGone = true)
//    }
//
//
//    private fun hideViews(
//        errorLayoutGone: Boolean = false,
//        progressBarGone: Boolean = false,
//        contentLayoutGone: Boolean = false
//    ) {
//        binding.errorLayout.isGone = errorLayoutGone
//        binding.progressBar.isGone = progressBarGone
//        binding.content.isGone = contentLayoutGone
//    }

//    private fun getTextError(error: ErrorType): String {
//        return when (error) {
//            ErrorType.SERVER_ERROR -> getString(R.string.server_error)
//            ErrorType.NO_INTERNET -> getString(R.string.no_internet_error)
//            else -> ""
//        }
//    }

    private fun setClickListener() {
        // binding.btnRetry.setOnClickListener { viewModel.loadDetails(productId) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}