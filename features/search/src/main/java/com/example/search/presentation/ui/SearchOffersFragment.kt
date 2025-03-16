package com.example.search.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.common.domain.model.Vacancy
import com.example.common.utills.ErrorType
import com.example.favorite.navigation.SearchNavigator
import com.example.search.databinding.FragmentSearchOffersBinding
import com.example.search.domain.model.Jobs
import com.example.search.domain.model.Offer
import com.example.search.presentation.adapter.OffersAdapter
import com.example.search.presentation.adapter.VacanciesAdapter
import com.example.search.presentation.state.SearchScreenState
import com.example.search.presentation.viewmodel.SearchViewModel
import com.example.uikit.R
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.core.parameter.parametersOf

class SearchOffersFragment : Fragment() {

    private var _binding: FragmentSearchOffersBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by activityViewModel()
    private lateinit var searchNavigator: SearchNavigator

    private val offersAdapter = OffersAdapter { offer ->
        if (offer.link.isNotBlank()) openLink(offer.link)
    }

    private val vacanciesAdapter = VacanciesAdapter(
        object : VacanciesAdapter.VacancyClickListener {
            override fun onVacancyClick(vacancy: Vacancy) {
                searchNavigator.openVacancyDetails()
            }

            override fun onFavoriteClick(vacancy: Vacancy) {
                searchViewModel.onFavoriteClick(vacancy)
            }
        }
    )

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

        initNavigator()
        initRecyclerView()
        observeViewModel()
        setClickListeners()
    }

    private fun initNavigator() {
        searchNavigator = get { parametersOf(findNavController()) }
    }

    private fun initRecyclerView() {
        binding.offersRv.adapter = offersAdapter
        binding.vacanciesRv.adapter = vacanciesAdapter
        binding.vacanciesRv.itemAnimator = null
    }

    private fun observeViewModel() {
        searchViewModel.screenState.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.SearchError -> {
                showError(state.type)
            }

            is SearchScreenState.Loading -> {
                showLoading()
            }

            is SearchScreenState.Content -> {
                showContent(state.jobs)
            }

            is SearchScreenState.JobsNotFound -> {
                showJobsNotFound(state.offers)
            }
        }
    }

    private fun showContent(jobs: Jobs) {
        hideViews(progressBarGone = true, errorLayoutGone = true)

        with(binding) {
            if (jobs.offers.isNotEmpty()) {
                offersAdapter.submitList(jobs.offers)
                offersRv.visibility = View.VISIBLE
            } else offersRv.visibility = View.GONE

            if (jobs.vacancies.isNotEmpty()) {
                vacanciesAdapter.submitList(jobs.vacancies.take(3))
                val moreVacancyBtnText = resources.getQuantityString(
                    R.plurals.vacancies_number_btn,
                    jobs.vacancies.size,
                    jobs.vacancies.size
                )
                moreVacanciesBtn.text = moreVacancyBtnText
            } else vacanciesRv.visibility = View.GONE
        }
    }

    private fun showJobsNotFound(offers: List<Offer>) {
        hideViews(progressBarGone = true, contentLayoutGone = true)
        with(binding) {
            if (offers.isNotEmpty()) {
                offersAdapter.submitList(offers)
                offersRv.visibility = View.VISIBLE
            }
            jobsNotFoundTv.text = getString(R.string.jobs_not_found)
            scrollView.isGone = false
            content.isGone = false
            jobsNotFoundTv.isGone = false
        }
    }

    private fun showError(error: ErrorType) {
        hideViews(progressBarGone = true, contentLayoutGone = true)
        binding.tvError.text = getTextError(error)
    }

    private fun showLoading() {
        hideViews(errorLayoutGone = true, contentLayoutGone = true)
    }

    private fun hideViews(
        errorLayoutGone: Boolean = false,
        progressBarGone: Boolean = false,
        contentLayoutGone: Boolean = false
    ) {
        with(binding) {
            errorLayout.isGone = errorLayoutGone
            progressBar.isGone = progressBarGone
            content.isGone = contentLayoutGone
            scrollView.isGone = contentLayoutGone
            moreVacanciesBtn.isGone = contentLayoutGone
            vacanciesRv.isGone = contentLayoutGone
            offersRv.isGone = contentLayoutGone
            vacancyHeading.isGone = contentLayoutGone
            jobsNotFoundTv.isGone = contentLayoutGone
        }
    }

    private fun getTextError(error: ErrorType): String {
        return when (error) {
            ErrorType.SERVER_THROWABLE -> getString(R.string.server_error)
            ErrorType.NO_INTERNET -> getString(R.string.no_internet_error)
            else -> ""
        }
    }

    private fun setClickListeners() {
        binding.btnRetry.setOnClickListener { searchViewModel.retryConnection() }
        binding.moreVacanciesBtn.setOnClickListener {
            findNavController().navigate(
                com.example.search.R.id.action_searchOffersFragment_to_searchAllVacanciesFragment
            )
        }
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