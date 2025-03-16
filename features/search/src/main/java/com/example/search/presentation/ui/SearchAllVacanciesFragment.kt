package com.example.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.common.domain.model.Vacancy
import com.example.common.utills.ErrorType
import com.example.favorite.navigation.SearchNavigator
import com.example.search.databinding.FragmentSearchAllVacanciesBinding
import com.example.search.domain.model.Jobs
import com.example.search.presentation.adapter.VacanciesAdapter
import com.example.search.presentation.state.SearchScreenState
import com.example.search.presentation.viewmodel.SearchViewModel
import com.example.uikit.R
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.core.parameter.parametersOf

class SearchAllVacanciesFragment : Fragment() {

    private var _binding: FragmentSearchAllVacanciesBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by activityViewModel()
    private lateinit var searchNavigator: SearchNavigator

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
        _binding = FragmentSearchAllVacanciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigator()
        initRecyclerView()
        observeViewModel()
    }

    private fun initNavigator() {
        searchNavigator = get { parametersOf(findNavController()) }
    }

    private fun initRecyclerView() {
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
            is SearchScreenState.Loading -> showLoading()

            is SearchScreenState.Content -> {
                showContent(state.jobs)
            }

            is SearchScreenState.JobsNotFound -> {
                showJobsNotFound()
            }

            is SearchScreenState.SearchError -> {
                showError(state.type)
            }
        }
    }

    private fun showContent(jobs: Jobs) {
        hideViews(progressBarGone = true, errorLayoutGone = true)

        with(binding) {
            vacanciesAdapter.submitList(jobs.vacancies)
            val vacancyValueText = resources.getQuantityString(
                R.plurals.vacancies_number_heading,
                jobs.vacancies.size,
                jobs.vacancies.size
            )
            vacanciesValueTv.text = vacancyValueText
        }
    }

    private fun showJobsNotFound() {
        hideViews(progressBarGone = true, contentLayoutGone = true)
        binding.tvError.text = getString(R.string.jobs_not_found)
        binding.btnRetry.isGone = true
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
        binding.errorLayout.isGone = errorLayoutGone
        binding.progressBar.isGone = progressBarGone
        binding.vacanciesValueTv.isGone = contentLayoutGone
        binding.sortingTv.isGone = contentLayoutGone
        binding.sortingIc.isGone = contentLayoutGone
        binding.vacanciesRv.isGone = contentLayoutGone
    }

    private fun getTextError(error: ErrorType): String {
        return when (error) {
            ErrorType.SERVER_THROWABLE -> getString(R.string.server_error)
            ErrorType.NO_INTERNET -> getString(R.string.no_internet_error)
            else -> ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}