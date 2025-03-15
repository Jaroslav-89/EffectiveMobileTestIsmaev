package com.example.search.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.search.databinding.FragmentSearchOffersBinding
import com.example.search.domain.model.Jobs
import com.example.search.presentation.adapter.OffersAdapter
import com.example.search.presentation.adapter.VacanciesAdapter
import com.example.search.presentation.state.SearchScreenState
import com.example.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchOffersFragment : Fragment() {

    private var _binding: FragmentSearchOffersBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModel()

    private val offersAdapter = OffersAdapter { offer ->
        if (offer.link.isNotBlank()) openLink(offer.link)
    }

    private val vacanciesAdapter = VacanciesAdapter { vacancy ->
        searchViewModel.onFavoriteClick(vacancy)
    }

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

        initRecyclerView()
        observeViewModel()
        // setRetryBtnClickListener()
    }

    private fun initRecyclerView() {
        binding.offersRv.adapter = offersAdapter
        binding.vacanciesRv.adapter = vacanciesAdapter
        binding.vacanciesRv.itemAnimator = null

    }

    private fun observeViewModel() {
        Log.d("xxx", "observeViewModel")
        searchViewModel.screenState.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Loading -> showLoading()
            is SearchScreenState.Content -> {
                Log.d(
                    "xxx",
                    "CONTENT : ${state.jobs.offers.toString() + state.jobs.vacancies.toString()}"
                )
                showContent(state.jobs)
            }


            is SearchScreenState.SearchError -> {
            }//showError(state.error)
        }
    }

    private fun showContent(jobs: Jobs) {
        hideViews(progressBarGone = true, errorLayoutGone = true)

        with(binding) {
            content.visibility = View.VISIBLE
            if (jobs.offers.isNotEmpty()) {
                offersAdapter.submitList(jobs.offers)
                offersRv.visibility = View.VISIBLE
            } else offersRv.visibility = View.GONE

            if (jobs.vacancies.isNotEmpty()) {
                vacanciesAdapter.submitList(jobs.vacancies.take(3))
                vacanciesRv.visibility = View.VISIBLE
                vacancyHeading.visibility = View.VISIBLE
                moreVacanciesBtn.visibility = View.VISIBLE
                moreVacanciesBtn.text = jobs.vacancies.size.toString()
            } else vacanciesRv.visibility = View.GONE

        }
    }

    //    private fun showError(error: ErrorType) {
//        hideViews(progressBarGone = true, contentLayoutGone = true)
//        binding.tvError.text = getTextError(error)
//    }
//
    private fun showLoading() {
        hideViews(errorLayoutGone = true, contentLayoutGone = true)
    }

    //
//
    private fun hideViews(
        errorLayoutGone: Boolean = false,
        progressBarGone: Boolean = false,
        contentLayoutGone: Boolean = false
    ) {
        // binding.errorLayout.isGone = errorLayoutGone
        binding.progressBar.isGone = progressBarGone
        //  binding.content.isGone = contentLayoutGone
    }

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

    private fun openLink(url: String) {
        Intent().apply {
            action = Intent.ACTION_VIEW
            data = url.toUri()
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            requireContext().startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}