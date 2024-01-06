package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.CategorySectionListAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.RecommendedCategorySectionListItem
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.SubCategorySectionListItem
import kotlinx.coroutines.flow.StateFlow

interface ChooseRentCategoryStrategy : CategorySectionListAdapter.Listener {
    val recommendedCategory: StateFlow<RecommendedCategorySectionListItem?>
    val categories: StateFlow<List<ListItem>>
    val subcategories: StateFlow<List<ListItem>>
    val selectedCategory: StateFlow<SubCategorySectionListItem?>
}