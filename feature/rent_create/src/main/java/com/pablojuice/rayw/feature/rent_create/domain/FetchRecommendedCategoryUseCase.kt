package com.pablojuice.rayw.feature.rent_create.domain

import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.RecommendedCategorySectionListItem
import javax.inject.Inject

class FetchRecommendedCategoryUseCase @Inject constructor() {
    operator fun invoke(): RecommendedCategorySectionListItem {
        return RecommendedCategorySectionListItem(
            id = 666,
            title = "Recommended Subcategory No. 666".asLabel()
        )
    }
}