package com.pablojuice.rayw.feature.rent_create.presentation.view.category

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChooseSubcategoryBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.CategorySectionListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentSubCategoryFragment :
    BasicFragment<FragmentRentCreateChooseSubcategoryBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChooseSubcategoryBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.recycler.adapter =
            CategorySectionListAdapter(viewModel, viewModel.subcategories.value)
    }
}