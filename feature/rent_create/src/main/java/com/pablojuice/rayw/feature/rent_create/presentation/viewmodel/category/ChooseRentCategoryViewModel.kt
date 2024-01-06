package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.domain.FetchAvailableCategoriesUseCase
import com.pablojuice.rayw.feature.rent_create.domain.FetchRecommendedCategoryUseCase
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.BackToCreateRentScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentSubCategoriesScreen
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.RecommendedCategorySectionListItem
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.SubCategorySectionListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChooseRentCategoryViewModel @Inject constructor(
    private val fetchCategories: FetchAvailableCategoriesUseCase,
    private val fetchRecommendedCategory: FetchRecommendedCategoryUseCase
) : BasicViewModel(), ChooseRentCategoryStrategy {

    private val _recommendedCategory = MutableStateFlow(fetchRecommendedCategory())
    override val recommendedCategory: StateFlow<RecommendedCategorySectionListItem?> =
        _recommendedCategory

    private val _categories = MutableStateFlow(fetchCategories())
    override val categories: StateFlow<List<ListItem>> = _categories

    private val _subcategories = MutableStateFlow(listOf<ListItem>())
    override val subcategories: StateFlow<List<ListItem>> = _subcategories

    private val _selectedCategory = MutableStateFlow<SubCategorySectionListItem?>(null)
    override val selectedCategory: StateFlow<SubCategorySectionListItem?> = _selectedCategory

    override fun showSubCategories(items: List<ListItem>) {
        _subcategories.value = items
        submitNavigationEvent(ToChooseRentSubCategoriesScreen())
    }

    override fun selectSubCategory(item: SubCategorySectionListItem) {
        _selectedCategory.value = item
        submitNavigationEvent(BackToCreateRentScreen())
    }
}