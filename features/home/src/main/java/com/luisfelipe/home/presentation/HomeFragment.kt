package com.luisfelipe.home.presentation

import com.luisfelipe.base.BaseFragment
import com.luisfelipe.home.R
import com.luisfelipe.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    successViewId = R.id.home_success,
    loadingViewId = R.id.home_loading,
    errorViewId = R.id.home_error
) {
    override fun onBind() = FragmentHomeBinding.inflate(layoutInflater)

    override fun FragmentHomeBinding.onViewCreated() {
        showError()
    }
}