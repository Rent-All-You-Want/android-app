package com.pablojuice.rayw.feature.rent_create.presentation.view.category

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChooseCategoryBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.CategorySectionListAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.CategorySectionTitleListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentCategoryFragment :
    BasicFragment<FragmentRentCreateChooseCategoryBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChooseCategoryBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
        setupList()
    }

    private fun setupList() {
        viewModel.categories.observe { categories ->
            val list = mutableListOf<ListItem>()
            viewModel.recommendedCategory.value?.let { list.add(it) }
            list.add(CategorySectionTitleListItem("Categories".asLabel()))
            list.addAll(categories)
            binding.recycler.adapter = CategorySectionListAdapter(viewModel, list)
        }
    }
}