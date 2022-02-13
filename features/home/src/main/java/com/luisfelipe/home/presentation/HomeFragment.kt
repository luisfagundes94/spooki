package com.luisfelipe.home.presentation

import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.luisfelipe.base.BaseFragment
import com.luisfelipe.home.R
import com.luisfelipe.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    successViewId = R.id.home_success_container,
    loadingViewId = R.id.home_loading_container,
    errorViewId = R.id.home_error_container
) {
    override fun onBind() = FragmentHomeBinding.inflate(layoutInflater)

    override fun FragmentHomeBinding.onViewCreated() {
        showSuccess()
        setUpMidiaTypesViewPager()
        setUpMidiaTypesTabLayout()
    }

    private fun setUpMidiaTypesViewPager() = with(binding) {
        val adapter = MidiaTypesViewPagerAdapter(this@HomeFragment)
        vpMidiaTypes.isSaveEnabled = false
        vpMidiaTypes.adapter = adapter
    }

    private fun setUpMidiaTypesTabLayout() = with(binding) {
        TabLayoutMediator(tlMidiaTypes, vpMidiaTypes) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_movies)
                1 -> tab.text = getString(R.string.title_shows)
            }
        }.attach()
    }
}