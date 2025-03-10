package com.example.effectivemobiletestismaev.root

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletestismaev.R
import com.example.effectivemobiletestismaev.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
//                R.id.playerFragment, R.id.createPlaylistFragment, R.id.detailPlaylistFragment -> {
//                    binding.bottomNavigationView.visibility = View.GONE
//                    binding.bottomDivider.visibility = View.GONE
                }

//                else -> {
//                    binding.bottomNavigationView.visibility = View.VISIBLE
//                    binding.bottomDivider.visibility = View.VISIBLE
//                }
            }
        }
    }
//}