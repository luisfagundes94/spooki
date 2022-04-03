package com.luisfagundes.shows.presentation.categories

import com.luisfagundes.base.BaseFragment
import com.luisfagundes.shows.R
import com.luisfagundes.shows.databinding.FragmentShowCategoriesBinding

class ShowCategoriesFragment : BaseFragment<FragmentShowCategoriesBinding>(
    successViewId = R.id.show_categories_success_container,
    loadingViewId = R.id.show_categories_loading_container,
    errorViewId = R.id.show_categories_error_container
) {
    override fun onBind() = FragmentShowCategoriesBinding.inflate(layoutInflater)

    override fun FragmentShowCategoriesBinding.onViewCreated() {
    }
}