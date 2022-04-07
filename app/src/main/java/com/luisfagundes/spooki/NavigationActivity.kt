package com.luisfagundes.spooki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luisfagundes.extensions.hideVisibility
import com.luisfagundes.extensions.showVisibility
import com.luisfagundes.spooki.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBinding()

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.movie_navigation,
                R.id.search_navigation,
                R.id.saved_navigation
            )
        )

        setupImmersiveView(navController, bottomNavView)

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
    }

    private fun setupImmersiveView(
        navController: NavController,
        bottomNavView: BottomNavigationView
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.luisfagundes.movies.R.id.movieDetailsFragment -> bottomNavView.hideVisibility()
                else -> bottomNavView.showVisibility()
            }
        }
    }

    private fun setViewBinding() {
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment_container).navigateUp() || super.onSupportNavigateUp()
}