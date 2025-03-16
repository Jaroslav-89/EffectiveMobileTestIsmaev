package com.example.effectivemobiletestismaev.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletestismaev.R
import com.example.effectivemobiletestismaev.databinding.ActivityRootBinding
import com.example.effectivemobiletestismaev.presentation.viewmodel.RootViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding
    private val viewModel: RootViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        setObserver()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.example.search.R.id.searchOffersFragment, com.example.search.R.id.searchAllVacanciesFragment -> {
                    binding.bottomNavigationView.menu.findItem(R.id.navSearchFragment).isChecked =
                        true
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.divider.visibility = View.VISIBLE
                }

                R.id.vacancyDetailFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                    binding.divider.visibility = View.GONE
                }

                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.divider.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setObserver() {
        viewModel.vacancyCount.observe(this) {
            renderBadgeCount(it)
        }
    }

    private fun renderBadgeCount(count: Int) {
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.navFavoriteFragment)
        if (count > 0) {
            badge.isVisible = true
            badge.number = count
        } else {
            badge.isVisible = false
        }
    }
}