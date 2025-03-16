package com.example.favorite.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.common.domain.model.Vacancy
import com.example.favorite.databinding.FragmentFavoriteVacanciesBinding
import com.example.favorite.navigation.FavoriteNavigator
import com.example.favorite.presentation.adapter.FavoriteVacanciesAdapter
import com.example.favorite.presentation.state.FavoriteScreenState
import com.example.favorite.presentation.viewmodel.FavoriteVacanciesViewModel
import com.example.uikit.R
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FavoriteVacanciesFragment : Fragment() {

    private var _binding: FragmentFavoriteVacanciesBinding? = null
    private val binding get() = _binding!!

    private val favoriteVacanciesViewModel: FavoriteVacanciesViewModel by viewModel()
    private lateinit var favoriteNavigator: FavoriteNavigator

    private val vacanciesAdapter = FavoriteVacanciesAdapter(
        object : FavoriteVacanciesAdapter.VacancyClickListener {
            override fun onVacancyClick(vacancy: Vacancy) {
                favoriteNavigator.openVacancyDetails()
            }

            override fun onFavoriteClick(vacancy: Vacancy) {
                favoriteVacanciesViewModel.onFavoriteClick(vacancy)
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteVacanciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigator()
        initRecyclerView()
        observeViewModel()
    }

    private fun initNavigator() {
        favoriteNavigator = get { parametersOf(findNavController()) }
    }

    private fun observeViewModel() {
        favoriteVacanciesViewModel.screenState.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: FavoriteScreenState) {
        when (state) {
            is FavoriteScreenState.Loading -> showLoading()
            is FavoriteScreenState.Content -> {
                showContent(state.vacancies)
            }

            is FavoriteScreenState.VacanciesNotFound -> {
                showEmpty()
            }
        }
    }

    private fun showLoading() {
        hideViews(emptyLayoutGone = true, contentLayoutGone = true)
    }

    private fun showEmpty() {
        hideViews(progressBarGone = true, contentLayoutGone = true)
    }

    private fun hideViews(
        emptyLayoutGone: Boolean = false,
        progressBarGone: Boolean = false,
        contentLayoutGone: Boolean = false
    ) {
        binding.emptyListTv.isGone = emptyLayoutGone
        binding.progressBar.isGone = progressBarGone
        binding.vacanciesValueTv.isGone = contentLayoutGone
        binding.vacanciesRv.isGone = contentLayoutGone
    }

    private fun showContent(vacancies: List<Vacancy>) {
        hideViews(progressBarGone = true, emptyLayoutGone = true)
        vacanciesAdapter.submitList(vacancies)
        val vacancyValueText = resources.getQuantityString(
            R.plurals.vacancies_number_heading,
            vacancies.size,
            vacancies.size
        )
        binding.vacanciesValueTv.text = vacancyValueText
    }

    private fun initRecyclerView() {
        binding.vacanciesRv.adapter = vacanciesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}